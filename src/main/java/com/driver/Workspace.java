package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE); // Workspace inbox capacity is set to maximum
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        ArrayList<Pair<LocalTime, Integer>> times = new ArrayList<>();

        for (Meeting meeting : calendar) {
            times.add(Pair.of(meeting.getStartTime(), 1));
            times.add(Pair.of(meeting.getEndTime(), -1));
        }
        Collections.sort(times, (a, b) -> a.getLeft().compareTo(b.getLeft()));

        int maxMeetings = 0;
        int currentMeetings = 0;

        for (Pair<LocalTime, Integer> time : times) {
            currentMeetings += time.getRight();
            maxMeetings = Math.max(maxMeetings, currentMeetings);
        }

        return maxMeetings;
    }
}
