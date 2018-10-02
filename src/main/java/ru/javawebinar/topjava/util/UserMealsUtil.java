package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        getFilteredWithExceeded(mealList, LocalTime.of(15, 0), LocalTime.of(21,0), 700);
//        .toLocalDate();
//        .toLocalTime();
    }

//ОБЫЧНЫЙ СПОСОБ. ПЕРЕБОР
    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> mealListResult = new ArrayList<>();
        for (int i = 1; i < mealList.size(); i++) {

            if((i+1)==mealList.size() || mealList.get(i).getDateTime().toLocalDate().getDayOfMonth()!=mealList.get(i-1).getDateTime().toLocalDate().getDayOfMonth()){
                int calDay=getCalFromDay(mealList, mealList.get(i-1).getDateTime().toLocalDate());
                boolean exceed = false;
                if (calDay>caloriesPerDay) {
                    exceed = true;
                }
                List<UserMealWithExceed> mealListPreResult = new ArrayList<>();
                mealListPreResult.addAll(setListWithExceed(mealList,  mealList.get(i-1).getDateTime().toLocalDate(), exceed));
                for (int j = 0; j < mealListPreResult.size(); j++) {
                    if (TimeUtil.isBetween(mealList.get(j).getDateTime().toLocalTime(), startTime, endTime)) {
                        mealListResult.add(mealListPreResult.get(j));
                    }
                }
            }
        }
        return mealListResult;
    }

    private static int getCalFromDay(List<UserMeal> mealList, LocalDate localDateTime){
        int cal = 0;
        for(UserMeal userMeal: mealList){
            if (userMeal.getDateTime().toLocalDate().getDayOfMonth()==localDateTime.getDayOfMonth()){
                cal=cal+userMeal.getCalories();
            }
        }
        return cal;
    }

    private static List<UserMealWithExceed> setListWithExceed(List<UserMeal> mealList, LocalDate localDateTime, boolean exceed){
        List<UserMealWithExceed> listMealWithExceedRez = new ArrayList<>();
        for(UserMeal userMeal: mealList){
            if (userMeal.getDateTime().toLocalDate().getDayOfMonth()==localDateTime.getDayOfMonth()){
                UserMealWithExceed userMealWithExceed =
                            new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed);
                    listMealWithExceedRez.add(userMealWithExceed);

            }
        }
        return listMealWithExceedRez;
    }
}
