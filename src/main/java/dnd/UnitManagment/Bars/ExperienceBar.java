package dnd.UnitManagment.Bars;

public class ExperienceBar extends Bar{
    public ExperienceBar(int current, int max){
        super(current, max);
    }

    public void gainExperience(int experience){
        this.current += experience;
    }

    public boolean checkExperience(){
        return this.current >= this.max;
    }

}
