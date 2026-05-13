package model;

import model.enums.Discipline;
import java.util.Comparator;

public class CompetitionSwimmerComparator implements Comparator<CompetitionSwimmer> {
    private Discipline discipline;

    public CompetitionSwimmerComparator(Discipline discipline){
        this.discipline = discipline;
    }

    @Override
    public int compare(CompetitionSwimmer o1, CompetitionSwimmer o2) {
        return o1.getPersonalRecord(discipline).compareTo(o2.getPersonalRecord(discipline));
    }
}
