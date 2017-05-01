/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.customer.Customer;
import entity.employee.Employee;
import entity.reservation.Reservation;
import entity.room.Rooms;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trach
 */
public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static Customer getUser() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return (Customer) session.getAttribute("user");
    }
    public static Employee getAdmin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return (Employee) session.getAttribute("admin");
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }
    public static int getResId() {
		HttpSession session = getSession();
		if (session != null)
			return (int) session.getAttribute("resId");
		else
			return 0;
	}
        
        public static int getRoomId() {
		HttpSession session = getSession();
		if (session != null)
			return (int) session.getAttribute("roomId");
		else
			return 0;
	}
        public static List<Rooms> getListChooseRooms() {
            HttpSession session = getSession();
            if (session != null)
                    return (List<Rooms>) session.getAttribute("listrooms");
            else
                    return null;
        }

        public static List<Reservation> getListBookedRooms() {
                HttpSession session = getSession();
                if (session != null)
                        return (List<Reservation>) session.getAttribute("roombooked");
                else
                        return null;
        }
        
        public static Reservation getUpdatereservation() {
            HttpSession session = getSession();
            if (session != null)
               return  (Reservation) session.getAttribute("update_res");
            else
               return null;
        }

     
}
