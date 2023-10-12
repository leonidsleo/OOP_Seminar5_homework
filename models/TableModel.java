package ru.geekbrains.lesson5.models;

import ru.geekbrains.lesson5.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TableModel implements Model {

    private Collection<Table> tables;

    /**
     * Получение списка всех столиков
     */
    @Override
    public Collection<Table> loadTables() {

        if (tables == null){
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    /**
     * Бронирование столика
     * @param reservationDate Дата бронирования
     * @param name Имя
     */
    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table: loadTables()) {
            if (table.getNo() == tableNo){
                Reservation reservation = new Reservation(reservationDate, name);
                table.getReservations().add(reservation);
                // System.out.printf("data %s name %s bron %s\n", reservationDate, name, reservation.getId());
                return reservation.getId();
            }
        }
        throw  new RuntimeException("Некорректный номер столика.");
    }

    

    /**
     * TODO: Доработать в рамках домашней работы
     * Отменить бронирование по номеру
     * @param oldReservation
     * @param reservationDate
     * @param tableNo
     * @param name
     * @return
     */

    @Override
    public int changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        for (Table table: loadTables()) {
            for (Reservation reservation: table.getReservations()) {
                if (oldReservation == reservation.getId()) {
                    reservation = null;
                }
            }
        if (table.getNo() == tableNo) {
            Reservation reservation = new Reservation(reservationDate, name);
            table.getReservations().add(reservation);
            return reservation.getId();
            }
        }
        throw  new RuntimeException("Некорректный номер столика.");
    }

    @Override
    public int noReservу(int oldReservation, String name) {
        for (Table table: loadTables()) {
            for (Reservation reservation: table.getReservations()) {
                if (oldReservation == reservation.getId()) {
                    reservation = null;
                }
                return reservation.getId();
            }
        }
        throw  new RuntimeException("Некорректный номер столика.");
    }

}