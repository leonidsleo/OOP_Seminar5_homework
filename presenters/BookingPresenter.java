package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;
import ru.geekbrains.lesson5.models.TableModel;
import ru.geekbrains.lesson5.views.BookingView;

import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private View view;

    public BookingPresenter(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setObserver(this);
    }

    /**
     * Получение списка всех столиков
     */
    private Collection<Table> loadTables(){
        return model.loadTables();
    }

    /**
     * Отобразить список столиков
     */
    public void updateUIShowTables(){
        view.showTables(loadTables());
    }

    /**
     * Отобразить результат бронирования на представлении
     * @param reservationId результат бронирования
     */
    private void updateUIShowReservationResult(int reservationId, String name, int tableNo){
        view.showReservationTableResult(reservationId, name, tableNo);
    }

    /**
     * Произошло событие, пользователь нажал на кнопку резерва столика
     * @param orderDate дата резерва
     * @param tableNo номер столика
     * @param name имя клиента
     */
    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateUIShowReservationResult(reservationNo, name, tableNo);
        }
        catch (RuntimeException e){
            updateUIShowReservationResult(-1, null, -1);
        }
    }

    /**
     * Отобразить результат изменения бронирования бронирования
     * @param reservationId результат бронирования
     */
    private void updateUIShowChangeReservationResult(int oldReservation, int reservationId, String name, int tableNo){
        view.showChangeReservationTable(oldReservation, reservationId, name, tableNo);
    }    

    @Override
    public void onChangeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        try {
            int reservationsNo = model.changeReservationTable(oldReservation, reservationDate, tableNo, name);
            updateUIShowChangeReservationResult(oldReservation, reservationsNo, name, tableNo);
        }
        catch (RuntimeException e){
            updateUIShowChangeReservationResult(-1, -1, null, -1);
        }
    }
}
