
package com.fkldigital.numerador;

import com.fkldigital.numerador.bean.User;
import com.fkldigital.numerador.dao.UserDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabioklopes [fabioklopes@live.com]
 */
public class Numerador {

    public static void main(String[] args) {
        List<User> dao = new UserDAO().findAll();
        
        
        System.out.println(dao);
    }
}
