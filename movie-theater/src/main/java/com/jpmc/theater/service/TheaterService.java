package com.jpmc.theater.service;

import com.jpmc.theater.model.Theater;

public interface TheaterService {
	void printScheduleInTextFormat(Theater Theater);
	void printScheduleInJsonFormat(Theater theater);
}
