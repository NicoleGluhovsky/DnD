package dnd.UnitManagment.Bars;

public class ExperienceBar extends Bar{
    public ExperienceBar(int current, int max){
        super(current, max);
    }

    public void gainExperience(int experience){
        this.current += experience;
    }

    public void checkExperience(){
        if(this.current > this.max){
            this.current = 0;
        }
    }

}
