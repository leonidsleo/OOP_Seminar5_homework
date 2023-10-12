package ru.geekbrains.lesson5.views;

import ru.geekbrains.lesson5.models.Table;
import ru.geekbrains.lesson5.presenters.View;
import ru.geekbrains.lesson5.presenters.ViewObserver;

import java.util.Collection;
import java.util.Date;

public class BookingView implements View {

    private ViewObserver observer;

    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }

    public void showTables(Collection<Table> tables) {
        for (Table table : tables) {
            System.out.println(table);
        }
    }

    @Override
    public void showReservationTableResult(int reservationId, String name, int tableNo) {
        if (reservationId > 0){
            System.out.printf("Столик успешно забронирован. %s,  номер вашей брони: #%d, столик номер %s\n", name, reservationId, tableNo);
        }
        else {
            System.out.println("Не удалось забронировать столик. Попробуйте повторить операцию позже.");
        }
    }

    /**
     * Действие клиента (пользователь нажал на кнопку бронирования), бронирование столика
     * @param orderDate дата бронирования
     * @param tableNo номер столика
     * @param name Имя
     */
    public void reservationTable(Date orderDate, int tableNo, String name){
        observer.onReservationTable(orderDate, tableNo, name);
    }

    /**
     * TODO: Реализовать в рамках домашней работы
     * Действие клиента (пользователь нажал на кнопку отмены бронирования)
     * Отменить резерв столика, забронировать на другое время
     * @param oldReservation старый номер брони
     * @param reservationDate дата бронирования
     * @param tableNo номер столика
     * @param name имя
     */



    @Override
    public void showChangeReservationTable(int oldReservation, int reservationId, String name, int tableNo) {
        if (reservationId > 0){
            System.out.printf("Бронь %s отменена. %s,  номер вашей новой брони: #%d, столик номер %s.\n", oldReservation, name, reservationId, tableNo);
        }
        else {
            System.out.println("Не удалось забронировать столик. Попробуйте повторить операцию позже.");
        }        
    }

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        observer.onChangeReservationTable(oldReservation, reservationDate, tableNo, name);
    }

    /**
     * Просто отменяем бронь, без смены 
     */


    @Override
    public void showNoReservy(int oldReservation, int reservationId, String name) {
        if (reservationId >= 0) {
            System.out.printf("%s. Бронь %s успешно отменена.", name, oldReservation);
        } else {
            System.out.println("Не удалось забронировать столик. Попробуйте повторить операцию позже.");
        }  
    }

    public void noReservу(int oldReservation, String name) {
        observer.onNoReservу(oldReservation, name);
    }

}
