package ru.kpfu.itis.gimaletdinova.dto;

public class WeatherDto {
    private String temperature;
    private String description;
    private String humidity;

    public WeatherDto(String temperature, String description, String humidity) {
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return  "temperature='" + temperature + '\'' +
                ", description='" + description + '\'' +
                ", humidity='" + humidity + '\'';
    }
}
