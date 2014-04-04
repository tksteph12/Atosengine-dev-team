/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources.service.utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author a573405
 */
public class Utils {

    public static List<String> parse( String motscles) {
        List<String> result = Arrays.asList(motscles.split(";"));
        return result;
    }

    
}
