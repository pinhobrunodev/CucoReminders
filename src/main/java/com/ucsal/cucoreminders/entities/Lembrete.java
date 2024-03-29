package com.ucsal.cucoreminders.entities;


import com.ucsal.cucoreminders.entities.User;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Comparator;

@ToString
@Entity
@Table(name = "tb_lembrete")
public class Lembrete implements Comparator<Lembrete> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String mensagem;

    public Lembrete() {
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_schedule_id")
    private TimeSchedule timeSchedule;


    public TimeSchedule getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(TimeSchedule timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    @Override
    public int compare(Lembrete o1, Lembrete o2) {
        return o1.getTimeSchedule().getDueDate().compareTo(o2.getTimeSchedule().getDueDate());
    }
}
