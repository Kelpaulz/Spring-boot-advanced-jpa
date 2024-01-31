package com.kc.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
    //define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Column(name = "hobby")
    private String hobby;
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL) //This instructorDetail refers to the instructorDetail property in the Instructor class
    private Instructor instructor;

    //create constructors

    public InstructorDetail(){

    }

    public InstructorDetail(String youtube_channel, String hobby) {
        this.youtubeChannel = youtube_channel;
        this.hobby = hobby;
    }


    //generate getter/setter methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtube_channel) {
        this.youtubeChannel = youtube_channel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    //generate toString() method

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtube_channel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
