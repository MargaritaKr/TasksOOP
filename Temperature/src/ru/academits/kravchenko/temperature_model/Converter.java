package ru.academits.kravchenko.temperature_model;

public class Converter {
    public static double getResult (double temperature, int from, int to){
        switch (from){
            case 0:
                switch (to){
                    case 1:
                        return (temperature * 9 / 5 + 32);
                    case 2:
                        return temperature + 273.15;
                }
            case 1:
                switch (to){
                    case 0:
                        return (temperature - 32) * 5 / 9;
                    case 2:
                        return (temperature - 32) * 5 / 9 + 273.15;
                }
            case 2:
                switch (to){
                    case 0:
                        return temperature - 273.15;
                    case 1:
                        return (temperature - 273.15) * 9 / 5 + 32;
                }
        }

        return temperature;
    }
}