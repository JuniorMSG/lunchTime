package com.cat.lunchTime.type;

import com.cat.lunchTime.exception.MemberException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

import static com.cat.lunchTime.constant.MemberConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.cat.lunchTime.constant.MemberConstant.MIN_SENIOR_EXPERIENCE_YEARS;
import static com.cat.lunchTime.exception.MemberErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

@AllArgsConstructor
@Getter
public enum MemberLevel {
//    NEW("신입", 0 , 0),
//    JUNIOR("주니어", 1, MAX_JUNIOR_EXPERIENCE_YEARS),
//    JUNGNIOR("중니어", MAX_JUNIOR_EXPERIENCE_YEARS +1,  MIN_SENIOR_EXPERIENCE_YEARS -1),
//    SENIOR("시니어", MIN_SENIOR_EXPERIENCE_YEARS, 70);
//    private final Integer minExperienceYears;
//    private final Integer maxExperienceYears;


    NEW("신입 개발자", years -> years == 0),
    JUNIOR("주니어 개발자", years -> years <= MAX_JUNIOR_EXPERIENCE_YEARS),
    JUNGNIOR("중니어 개발자", years -> years > MAX_JUNIOR_EXPERIENCE_YEARS
            && years < MIN_SENIOR_EXPERIENCE_YEARS),
    SENIOR("시니어 개발자", years -> years >= MIN_SENIOR_EXPERIENCE_YEARS);
    private final String description;

    private final Function<Integer, Boolean> validateFunction;

    public void validateExperienceYears(Integer years) {
        if (!validateFunction.apply(years))
            throw new MemberException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
    }

}
