package com.les.springevent;

import lombok.Data;

/**
 * @ClassName: AnnotationMealEvent
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/26 13:42
 */
@Data
public class GeneralObject {
    private String objectName;

    public GeneralObject(String objectName) {
        this.objectName = objectName;
    }
}
