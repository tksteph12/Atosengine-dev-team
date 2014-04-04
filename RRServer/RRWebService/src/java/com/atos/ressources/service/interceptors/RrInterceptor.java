/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources.service.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author a573405
 */
public class RrInterceptor {
    
    @AroundInvoke
    public void checkArguments(InvocationContext context){
        
    }
}
