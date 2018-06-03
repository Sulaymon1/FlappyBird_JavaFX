package ru.itis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Date 02.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Message {
    private String username;
    private String message;
}
