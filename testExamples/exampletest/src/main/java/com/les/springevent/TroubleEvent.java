package com.les.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: TroubleEvent
 * @Description:
 * @Author: king
 */
public class TroubleEvent extends ApplicationEvent {
    public TroubleEvent(Object source) {
        super(source);
    }
}