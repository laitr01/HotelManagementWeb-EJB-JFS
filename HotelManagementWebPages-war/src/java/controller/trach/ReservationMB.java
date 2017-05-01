/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.trach;

import entity.customer.Customer;
import entity.customer.CustomerFacadeLocal;
import entity.reservation.Reservation;
import entity.reservation.ReservationDetail;
import entity.reservation.ReservationDetailFacadeLocal;
import entity.reservation.ReservationDetailPK;
import entity.reservation.ReservationFacadeLocal;
import entity.room.RoomType;
import entity.room.Rooms;
import entity.room.RoomsFacadeLocal;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sun.util.calendar.Gregorian;
import utils.Contanst;
import utils.SessionUtils;

/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class ReservationMB {

    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private RoomsFacadeLocal roomsFacade;
    @EJB
    private ReservationDetailFacadeLocal reservationDetailFacade;
    @EJB
    private ReservationFacadeLocal reservationFacade;

    private int reservationId;
    private String start;
    private String end;
    private int numberOfPeople;
    private int paid;
    private String status;
    private int customerId;
    private int roomId;
    private int roomtype;
    private int numberOfChild;
    private int numberOfAdults;

    private static final int A_DAY_MILISECONDS = 24 * 60 * 60 * 1000;

    private void resetListRoom(List<Reservation> listRooms, Customer customer) {
        for (Reservation reservation : listRooms) {
            reservation.setCustomerID(customer);
        }
    }

    public enum RESERVATION_STATUS {

        CHECK_IN("check_in"),
        CHECK_OUT("check_out"),
        NEW("new"),
        EXPIRED("expired");
        private String value;

        private RESERVATION_STATUS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public ReservationMB() {

    }

    private List<Rooms> allEmptyRooms;

    public String cannableReservation() {
        if (convertStringToDate(start).getTime() > convertStringToDate(end).getTime()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Start date must be less than end date."));
            return "index";
        }
        allEmptyRooms = availableRooms();
        if (allEmptyRooms != null) {
            return "index";
        }
        allEmptyRooms = new ArrayList<>();
        return "index";
    }

    public List<Rooms> availableRooms() {
        List<Rooms> allRooms = reservationFacade.getAllRooms();
        List<Reservation> allReservation = reservationFacade.findAll();
        HashSet<Integer> setIds = new HashSet<>();

        int roomType = roomtype;

        long checkinTimeInLong = convertStringToDate(start).getTime();
        long checkoutTimeInLong = convertStringToDate(end).getTime();
        System.out.println(checkinTimeInLong + "," + checkoutTimeInLong);

        for (Reservation reservation : allReservation) {
            System.out.println(reservation.getEndDate().getTime() + "");
            long reservationStart = reservation.getStartDate().getTime();
            long reservationEnd = reservation.getEndDate().getTime();

            if ((!reservation.getStatus().equals(RESERVATION_STATUS.CHECK_OUT.getValue())
                    && !reservation.getStatus().equals(RESERVATION_STATUS.EXPIRED.getValue()))
                    && ((reservationStart <= checkinTimeInLong && reservationEnd >= checkinTimeInLong)
                    || (reservationStart <= checkoutTimeInLong && reservationEnd >= checkoutTimeInLong)
                    || (reservationStart <= checkinTimeInLong && reservationEnd >= checkoutTimeInLong)
                    ||(reservationStart >= checkinTimeInLong && reservationEnd <= checkoutTimeInLong))) {
                setIds.add(reservation.getRoomID().getRoomID());
            }
        }
        List<Rooms> emptyRooms = new ArrayList<>();
        for (Rooms rooms : allRooms) {
            RoomType rType = rooms.getRoomTypeID();

            if (!setIds.contains(rooms.getRoomID())
                    && rType.getRoomTypeID() == roomType
                    && rType.getNumOfAdult() >= numberOfAdults
                    && rType.getNumOfChild() >= numberOfChild) {
                emptyRooms.add(rooms);
            }
        }
        return emptyRooms;
    }

    public Date convertStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ReservationMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            Logger.getLogger(ReservationMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String startDate) {
        this.start = startDate;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String endDate) {
        this.end = endDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(int roomtype) {
        this.roomtype = roomtype;
    }

    public int getNumberOfChild() {
        return numberOfChild;
    }

    public void setNumberOfChild(int numberOfChild) {
        this.numberOfChild = numberOfChild;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public List<Rooms> getAllEmptyRooms() {
        return allEmptyRooms;
    }

    public void setAllEmptyRooms(List<Rooms> allEmptyRooms) {
        this.allEmptyRooms = allEmptyRooms;
    }

    public String redirectToListUsersReservation() {
        Customer username = SessionUtils.getUser();
        if (username == null) {
            return "/faces/login.xhtml?faces-redirect=true";
        }
        //listUsersReservation(username);

        return "/faces/users/trach/users_reservation.xhtml?faces-redirect=true";
    }

    public List<Reservation> listUsersReservation() {
        Customer cus = SessionUtils.getUser();
        if (cus == null) {
            return null;
        }
        return reservationFacade.getUserReservation(cus.getEmail());
    }
    private List<ReservationDetail> lisResDetails;

    public String redirectToReservationDetail(int resId) {
        //lisResDetails = reservationDetailFacade.findResDetailByResId(resId);
        return "/faces/users/trach/list_detail_reservations.xhtml?faces-redirect=true";
    }

    public List<ReservationDetail> lisResDetails() {
        return lisResDetails;
    }

    public void setLisResDetails(List<ReservationDetail> lisResDetails) {
        this.lisResDetails = lisResDetails;
    }

    public String addRoomToReservation(int roomId) {
        Customer customer = SessionUtils.getUser();
        Reservation reservation = new Reservation(
                convertStringToDate(start),
                convertStringToDate(end),
                numberOfChild + numberOfAdults,
                BigDecimal.ZERO,
                RESERVATION_STATUS.NEW.getValue(),
                customer,
                roomsFacade.find(roomId));
        return addReservationToList(reservation);
    }

    public String addReservationToList(Reservation reser) {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listReservation = (List<Reservation>) session.getAttribute("listres");

        if (listReservation == null) {
            listReservation = new ArrayList<>();
        }

        List<Reservation> listTemp = new ArrayList<>();

        listTemp.addAll(listReservation);

        //debug
        for (Reservation reservation : listTemp) {
            System.out.println("Room before: " + reservation.getRoomID().getRoomName());
        }

        for (Reservation reservation : listReservation) {
            if (reser.getRoomID().getRoomID().intValue() == reservation.getRoomID().getRoomID().intValue()) {
                if (((reservation.getStartDate().getTime() == reser.getStartDate().getTime()
                        && reservation.getEndDate().getTime() == reser.getEndDate().getTime()))) {
                    return "";
                } else if ((reservation.getStartDate().getTime() <= reser.getStartDate().getTime()
                        && reservation.getEndDate().getTime() >= reser.getEndDate().getTime())) {
                    System.out.println("First: " + reservation.getRoomID().getRoomName());
                    listTemp.remove(reservation);
//                    listTemp.add(reser);
//                    session.setAttribute("listrooms", listTemp);
//                    return "";
                } else if ((reservation.getStartDate().getTime() >= reser.getStartDate().getTime()
                        && reservation.getEndDate().getTime() <= reser.getEndDate().getTime())) {
                    System.out.println("Two: " + reservation.getRoomID().getRoomName());
                    listTemp.remove(reservation);
//                    listTemp.add(reser);
//                    session.setAttribute("listrooms", listTemp);
//                    return "";
                } else if ((reservation.getStartDate().getTime() <= reser.getEndDate().getTime()
                        && reservation.getStartDate().getTime() >= reser.getStartDate().getTime())) {
                    System.out.println("three: " + reservation.getRoomID().getRoomName());
                    listTemp.remove(reservation);
//                    listTemp.add(reser);
//                    session.setAttribute("listrooms", listTemp);
//                    return "";
                } else if ((reservation.getEndDate().getTime() >= reser.getStartDate().getTime()
                        && reservation.getEndDate().getTime() <= reser.getEndDate().getTime())) {
                    System.out.println("Four: " + reservation.getRoomID().getRoomName());
                    listTemp.remove(reservation);
//                    listTemp.add(reser);
//                    session.setAttribute("listrooms", listTemp);
//                    return "";
                }
//                else if ((reservation.getStartDate().getTime() > reser.getStartDate().getTime()
//                        && reservation.getStartDate().getTime() >= reser.getEndDate().getTime())
//                        || (reservation.getEndDate().getTime() <= reser.getStartDate().getTime()
//                        && reservation.getEndDate().getTime() < reser.getEndDate().getTime())) {
//                    listTemp.add(reser);
//                    session.setAttribute("listrooms", listTemp);
//                    return "";
            }
        }
        //if(!listTemp.contains(reser))
        listTemp.add(reser);
        //debug
        for (Reservation reservation : listTemp) {
            System.out.println("Room after: " + reservation.getRoomID().getRoomName());
        }

        session.setAttribute("listres", listTemp);
        return "";
    }

    public int checkListBookRoom() {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listRoom = (List<Reservation>) session.getAttribute("listres");
        if (listRoom != null && listRoom.size() > 0) {
            return listRoom.size();
        }
        return 0;
    }

    public List<Reservation> listBookRoom() {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listRoom = (List<Reservation>) session.getAttribute("listres");
        if (listRoom != null && listRoom.size() > 0) {
            return listRoom;
        }
        return null;
    }

    public String removeFromList(int roomId) {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listReservation = (List<Reservation>) session.getAttribute("listres");
        if (listReservation.size() == 0) {
            return "/faces/index?faces-redirect=true";
        }
        List<Reservation> listTemp = new ArrayList<>();

        listTemp.addAll(listReservation);

        for (Reservation reservation : listReservation) {
            if (roomId == reservation.getRoomID().getRoomID().intValue()
                    && (reservation.getEndDate().getTime() == convertStringToDate(end).getTime()
                    && reservation.getStartDate().getTime() == convertStringToDate(start).getTime())) {
                listTemp.remove(reservation);
            }
        }
        session.setAttribute("listres", listTemp);
        return "";
    }

    public String removeFromListRes(Reservation res) {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listReservation = (List<Reservation>) session.getAttribute("listres");
        if (listReservation.size() == 0) {
            return "/faces/index?faces-redirect=true";
        }
        List<Reservation> listTemp = new ArrayList<>();

        listTemp.addAll(listReservation);

        for (Reservation reservation : listReservation) {
            if (res.getRoomID().getRoomID() == reservation.getRoomID().getRoomID().intValue()
                    && (reservation.getEndDate().getTime() == res.getEndDate().getTime()
                    && reservation.getStartDate().getTime() == res.getStartDate().getTime())) {
                listTemp.remove(reservation);
            }
        }
        session.setAttribute("listres", listTemp);
        return "";
    }

    public boolean checkInList(Rooms room) {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listRoom = (List<Reservation>) session.getAttribute("listres");
        if (listRoom == null) {
            return false;
        }
        for (Reservation reservation : listRoom) {
            if (room.getRoomID().intValue() == reservation.getRoomID().getRoomID().intValue()
                    && ((reservation.getStartDate().getTime() == convertStringToDate(start).getTime()
                    && reservation.getEndDate().getTime() == convertStringToDate(end).getTime()))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkResInList(Reservation res) {
        HttpSession session = SessionUtils.getSession();
        List<Reservation> listRoom = (List<Reservation>) session.getAttribute("listres");
        if (listRoom == null) {
            return false;
        }
        for (Reservation reservation : listRoom) {
            if (res.getRoomID().getRoomID().intValue() == reservation.getRoomID().getRoomID().intValue()) {
                return true;
            }
        }
        return false;
    }

    public String redirectToMakeReservationPage() {
        return "/faces/users/trach/make_reservation.xhtml?faces-redirect=true";
    }

    public String redirectToIndexPage() {
        return "/faces/index.xhtml?faces-redirect=true";
    }

    public String makeReservation() {
        List<Reservation> listRooms = listBookRoom();

        if (SessionUtils.getUser() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("You must have login before make reservation."));
            Contanst.CHECKOUT = "checkout";

            return "/faces/users/vu/login?faces-redirect=true";
        }
        if (listRooms == null || listRooms.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("You must have choose available rooms first."));
            return "/faces/index?faces-redirect=true";
        } else {
            resetListRoom(listRooms, SessionUtils.getUser());
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("listres", listRooms);
            return "/faces/users/trach/reservation_form?faces-redirect=true";
        }
    }

    public long calDay(Date start, Date end) {
        return (end.getTime() - start.getTime()) / A_DAY_MILISECONDS;
    }

    public String storeYourReservations() {
        List<Reservation> listRes = listBookRoom();
        final List<Reservation> listRoomBooked = new ArrayList<>();
        final HttpSession session = SessionUtils.getSession();

        new BookRooms(listRes, new ReservationCallback() {

            @Override
            public void onSuccess(String message) {
                System.out.println(message);

                session.setAttribute("listres", null);

            }

            @Override
            public void onFailure(Reservation listRes) {
                listRoomBooked.add(listRes);
                System.out.println(listRes.getRoomID().getRoomName());
                session.setAttribute("roombooked", listRoomBooked);
            }
        }).start();
        return "/faces/users/trach/users_reservation.xhtml?faces-redirect=true";
    }

    public String deleteResAdmin(int resID) {
        Reservation res = reservationFacade.find(resID);
        if (res != null) {
            if (res.getStatus().equals(RESERVATION_STATUS.NEW.getValue())) {
                reservationDetailFacade.remove(reservationDetailFacade.
                        findResDetailByResId(resID));
                reservationFacade.remove(res);
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("You can only delete new reservations."));
                return "";
            }
        }
        return "/faces/admin/reservationAdmin.xhtml?faces-redirect=true";
    }

    public String deleteRes(int resID) {
        Reservation res = reservationFacade.find(resID);
        if (res != null) {
            if (res.getStatus().equals(RESERVATION_STATUS.NEW.getValue())) {
                reservationDetailFacade.remove(reservationDetailFacade.
                        findResDetailByResId(resID));
                reservationFacade.remove(res);
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("You can only delete new reservations."));
                return "";
            }
        }
        return "/faces/users/trach/users_reservation.xhtml?faces-redirect=true";
    }

    //chi update dc new va checkin
    public String updateRes(int resID) {
        Reservation res = reservationFacade.find(resID);
        HttpSession session = SessionUtils.getSession();
        if (res != null) {
            if (res.getStatus().equals(RESERVATION_STATUS.NEW.getValue())) {
                session.setAttribute("update_res", res);
                res.setStatus(RESERVATION_STATUS.CHECK_IN.getValue());
                if (reservationFacade.updateStatus(res)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Update successfully."));
                    return "/faces/admin/reservationAdmin.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Update failed."));
                    return "";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("You can only update new reservations."));
                return "";
            }
        }

        return "/faces/admin/reservationAdmin.xhtml?faces-redirect=true";
    }

    public Reservation reservationUpdate() {
        //HttpSession session = SessionUtils.getSession();
        return SessionUtils.getUpdatereservation();
    }

    public List<Reservation> getReservationHistory() {
        return reservationFacade.findAll();
    }

    public boolean checkAvailableRes(Reservation res) {

        List<Reservation> resHistory = getReservationHistory();

        long checkinTimeInLong = res.getStartDate().getTime();
        long checkoutTimeInLong = res.getEndDate().getTime();

        System.out.println(checkinTimeInLong + "," + checkoutTimeInLong);

        for (Reservation reservation : resHistory) {
            System.out.println(reservation.getEndDate().getTime() + "");
            long reservationStart = reservation.getStartDate().getTime();
            long reservationEnd = reservation.getEndDate().getTime();

            if ((res.getRoomID().getRoomID().intValue() == reservation.getRoomID().getRoomID().intValue())
                    && ((reservationStart <= checkinTimeInLong && reservationEnd >= checkinTimeInLong)
                    || (reservationStart <= checkoutTimeInLong && reservationEnd >= checkoutTimeInLong)
                    || (reservationStart <= checkinTimeInLong && reservationEnd >= checkoutTimeInLong))) {
                return false;
            }
        }
        return true;
    }

    public class BookRooms extends Thread {

        List<Reservation> listRes;
        ReservationCallback callback;

        public BookRooms(List<Reservation> listRes, ReservationCallback callback) {
            this.listRes = listRes;
            this.callback = callback;
            //start();
        }

        @Override
        public void run() {
            new Process().store(listRes, callback);
        }

    }

    class Process {

        synchronized public void store(List<Reservation> listRes, ReservationCallback callback) {

            for (Reservation reservation : listRes) {
                if (checkAvailableRes(reservation)) {
                    try {
                        reservationFacade.create(reservation);
                        ReservationDetail detail
                                = new ReservationDetail(new ReservationDetailPK(
                                                reservation.getRoomID().getRoomID(),
                                                reservation.getReservationID()));
                        detail.setDate(reservation.getStartDate());
                        detail.setRate(reservation.getRoomID().getRoomTypeID().getRoomRate());
                        reservationDetailFacade.create(detail);

                        callback.onSuccess("Insert to db succeed.");
                    } catch (Exception e) {
                        continue;
                    }
                } else {
                    //listRoomBooked.add(reservation);
                    callback.onFailure(reservation);
                }
            }
        }
    }

    public List<Reservation> showReservation() {
        return reservationFacade.findAll();
    }

    public boolean checkStatus(String status) {
        return status.equals("new");
    }

    private String[] monthOfYear = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    };

    public String generateData() {
        List<Reservation> list = reservationFacade.findAll();
        //index tuong ung trong mang
        int currentMonth = getCurrentMonth();
        //group by reservation theo thang cho toi thang hien tai
        HashMap<String, Integer> listData = new HashMap<>();
        for (Reservation reservation : list) {
            int index = getMonthOfDate(reservation.getStartDate());
            if (index <= currentMonth) {
                if (!listData.containsKey(monthOfYear[index])) {
                    listData.put(monthOfYear[index], 1);
                } else {
                    Integer value = listData.get(monthOfYear[index]);
                    listData.replace(monthOfYear[index], ++value);
                    System.out.println("Key:" + monthOfYear[index] + ", value: " + value);
                }
            }
        }
        return generateJson(listData);
    }

    public int getCurrentMonth() {
        GregorianCalendar gregorian = new GregorianCalendar();
        return gregorian.get(Calendar.MONTH);
    }

    public int getMonthOfDate(Date date) {
        //tru cho 1 de ra duoc index trong mang
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public String generateJson(Map<String, Integer> list) {
        String label ="";
        String value ="";
        for (Iterator it = list.keySet().iterator(); it.hasNext();) {
            String month = (String)it.next();
            label += "\""+month+"\""+",";
            value += String.valueOf(list.get(month))+",";
        }
        System.out.println("label: "+label);
        System.out.println("value: "+value);
        label = label.substring(0, label.lastIndexOf(","));
        value = value.substring(0, value.lastIndexOf(","));
        return " {"
                + "labels: ["+ label +"],"
                + "datasets: ["
                + "{"
                + "label: \"Number of reservation\","
                + "fill: false,"
                + "lineTension: 0.1,"
                + "backgroundColor: \"rgba(75,192,192,0.4)\","
                + "borderColor: \"rgba(75,192,192,1)\","
                + "borderCapStyle: 'butt',"
                + "borderDash: [],"
                + "borderDashOffset: 0.0,"
                + "borderJoinStyle: 'miter',"
                + "pointBorderColor: \"rgba(75,192,192,1)\","
                + "pointBackgroundColor: \"#fff\","
                + "pointBorderWidth: 1,"
                + "pointHoverRadius: 5,"
                + "pointHoverBackgroundColor: \"rgba(75,192,192,1)\","
                + "pointHoverBorderColor: \"rgba(220,220,220,1)\","
                + "pointHoverBorderWidth: 2,"
                + "pointRadius: 1,"
                + "pointHitRadius: 10,"
                + "data: ["+ value +"],"
                + "spanGaps: false"
                + "}"
                + "]"
                + "};";
    }
}
