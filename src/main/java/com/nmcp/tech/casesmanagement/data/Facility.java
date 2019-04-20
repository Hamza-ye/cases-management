package com.nmcp.tech.casesmanagement.data;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hamza on 2019-02-11.
 */

/*"YYYY-'W'ww"
String input = "20130507";
String format = "yyyyMMdd";

SimpleDateFormat df = new SimpleDateFormat(format);
Date date = df.parse(input);

*/

@Entity
@Data
@Deprecated
public class Facility extends AbstractEntity {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true)
    private String code;
    private String name;
    private String description;

    @ManyToOne
    private District district; //    @JoinColumn(name = "district_id")

    @Override
    public Serializable getId() {
        return this.id;
    }

    public enum Type {CENTER, CLINIC, HOSPITAL, DISPENSARY, UNIT, COMPLEX}

    public enum Owner {PUBLIC, PRIVATE}

}
