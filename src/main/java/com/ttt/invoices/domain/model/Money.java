package com.ttt.invoices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Money implements Comparable<Money> {
    private final Long cents;

    public Money() {
        cents = 0L;
    }

    public Double dollars() {
        return roundToDecimals(cents.doubleValue() / 100, 2);
    }

    public Money times(Long value) {
        double result = this.cents.doubleValue() * value.doubleValue();
        return new Money(roundToDecimals(result, 2).longValue());
    }

    public Money times(Double value) {
        double multiply = this.cents.doubleValue() * value;
        Double result = roundToDecimals(multiply, 2);
        return new Money(result.longValue());
    }

    public Money times(Money money) {
        double result = this.cents.doubleValue() * money.getCents().doubleValue();
        return new Money(roundToDecimals(result, 2).longValue());
    }

    public Money plus(Money money) {
        return new Money(cents + money.getCents());
    }

    public Money minus(Money money) {
        return new Money(cents - money.getCents());
    }

    public Money div(Money money) {
        double currentCents = cents.doubleValue();
        double otherCents = money.cents.doubleValue();
        long result = Math.round(currentCents / otherCents);
        return new Money(result);
    }

    public Money div(Long value) {
        double currentCents = cents.doubleValue();
        double otherCents = value.doubleValue();
        long result = Math.round(currentCents / otherCents);
        return new Money(result);
    }

    public Money div(Double value) {
        double currentCents = cents.doubleValue();
        long result = Math.round(currentCents / value);
        return new Money(result);
    }

    public Money div(Integer value) {
        double currentCents = cents.doubleValue();
        long result = Math.round(currentCents / value);
        return new Money(result);
    }

    public Money divRoundUp(Integer value) {
        double currentCents = cents.doubleValue();
        double div = currentCents / value;
        BigDecimal bigDecimal = new BigDecimal(div);
        long result = bigDecimal.setScale(0, RoundingMode.UP).longValue();
        return new Money(result);
    }

    public static Money from(Double value) {
        long multiply = Math.round(value * 100);
        return new Money(multiply);
    }

    public static Money from(Long value) {
        return new Money(value * 100);
    }

    public static Money from(Integer value) {
        return new Money(value.longValue() * 100);
    }

    @Override
    public int compareTo(Money o) {
        return cents.compareTo(o.cents);
    }

    public int compareTo(Long value) {
        return cents.compareTo(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Money) {
            Money money = (Money) o;
            return Objects.equals(money.cents, cents);
        } else if (o instanceof Long) {
            return Objects.equals(o, cents);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return cents.hashCode();
    }

    public Double percentageFrom(Money other) {
        if (other.getCents() == 0L) {
            return 0.0;
        }
        double doubleResult = cents.doubleValue() / other.getCents().doubleValue();
        return roundToDecimals(doubleResult * 100, 2);
    }

    public Double percentageFromWithoutRound(Money other) {
        if (other.getCents() == 0L) {
            return 0.0;
        }
        double doubleResult = cents.doubleValue() / other.getCents().doubleValue();
        return doubleResult * 100;
    }

    public Money takePercentage(Double percentage) {
        return times(percentage).div(100);
    }

    public Money takeIncludedPercentage(Double percentage) {
        long result = Math.round(cents.doubleValue() / (100 + percentage) * percentage);
        return new Money(result);
    }

    public static Double roundToDecimals(final double value, final int numDecimalPlaces) {
        double factor = Math.pow(10.0, numDecimalPlaces);
        long multiplyFactor = Math.round(value * factor);
        BigDecimal divisionFactor = new BigDecimal(multiplyFactor / factor);
        return divisionFactor.stripTrailingZeros().doubleValue();
    }
}
