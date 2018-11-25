package com.les.spring.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: exampletest
 * @Package: com.les.spring.bean
 * @ClassName: SgtPeppers
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/17 11:09
 * @UpdateUser: king
 * @UpdateDate: 2018/11/17 11:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Data
@Component(value="lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {

    private String title = "SgtPeppers titles";
    private String artist = "SgtPeppers";
    @Override
    public void play() {
        log.info("playing " + title + " by " + artist);
    }
}
