package com.cat.lunchTime.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;
@AllArgsConstructor
@Getter
public enum JobLevel {
    NEW("신입", years -> years == 0),
    JUNIOR("주니어", years -> years <= MAX_JUNIOR_EXPERIENCE_YEARS),
    JUNGNIOR("중니어", years -> years > MAX_JUNIOR_EXPERIENCE_YEARS
            && years < MIN_SENIOR_EXPERIENCE_YEARS),
    SENIOR("시니어", years -> years >= MIN_SENIOR_EXPERIENCE_YEARS);

    private final String description;
    private final Function<Integer, Boolean> validateFunction;

    public void validateExperienceYears(Integer years) {
        if (!validateFunction.apply(years))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
    }
}
