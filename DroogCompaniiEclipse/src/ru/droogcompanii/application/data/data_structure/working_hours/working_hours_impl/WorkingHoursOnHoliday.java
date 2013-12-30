package ru.droogcompanii.application.data.data_structure.working_hours.working_hours_impl;

import java.io.Serializable;

import ru.droogcompanii.application.data.data_structure.working_hours.Time;
import ru.droogcompanii.application.data.data_structure.working_hours.WorkingHours;

/**
 * Created by Leonid on 19.12.13.
 */
class WorkingHoursOnHoliday implements WorkingHours, Serializable {
	private static final long serialVersionUID = -5311642657006459627L;
	
	private final String messageToShow;

    public WorkingHoursOnHoliday(String messageToShow) {
        this.messageToShow = messageToShow;
    }

    @Override
    public boolean includes(Time time) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WorkingHoursOnHoliday)) {
            return false;
        }
        WorkingHoursOnHoliday other = (WorkingHoursOnHoliday) obj;
        return messageToShow.equals(other.messageToShow);
    }

    @Override
    public int hashCode() {
        return messageToShow.hashCode();
    }

    @Override
    public String toString() {
        return messageToShow;
    }
}
