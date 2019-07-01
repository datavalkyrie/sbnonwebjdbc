/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.mavenproject3.service;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloMessageService {

    @Value("${name:unknown}")
    private String name;

    @CaptureTransaction
    @CaptureSpan
    public String getMessage() {
        return getMessage(name);
    }

    @CaptureTransaction
    @CaptureSpan
    public String getMessage(String name) {
        return "Hello " + name;
    }

}