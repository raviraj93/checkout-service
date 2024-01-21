package com.idealo.config;

import java.time.LocalDate;

public class DateProviderImpl implements DateProvider {
    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
