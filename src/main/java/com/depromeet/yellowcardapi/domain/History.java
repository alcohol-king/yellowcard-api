package com.depromeet.yellowcardapi.domain;

import com.depromeet.yellowcardapi.utils.HelperFunctions;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;

@Entity
@Table(name = "drink_history")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_history_id")
    private Long id;

    @Column(name = "user_id", updatable= false)
    private Long userId;

    @Column
    private Integer beer;

    @Column
    private Integer soju;

    @Column
    private Integer wine;

    @Column
    private Integer makgeolli;

    @Column
    private LocalDate drunkAt;

    private HashMap<String, String> most;

    public HashMap<String, String> getMost() {
        String maxTypeName = "";
        int maxTypeAmount = 0;

        for (Method m : getClass().getMethods()) {
            if (m.getName().startsWith("get") && m.getReturnType().equals(Integer.class)) {
                try {
                    if (!HelperFunctions.isNullOrZero(m.invoke(this)) && (int) m.invoke(this) > maxTypeAmount) {
                        maxTypeName = m.getName().substring(3).toLowerCase();
                        maxTypeAmount = (int) m.invoke(this);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        most = new HashMap<>();
        most.put("type", maxTypeName);
        most.put("drunk_amount", String.valueOf(maxTypeAmount));

        return most;
    }

}
