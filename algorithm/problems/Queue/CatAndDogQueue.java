package algorithm.problems.Queue;

/**
 * CatAndDogQueue
 */
public class CatAndDogQueue {

    //add(cat or dog) enqueueAll() dequeueDog
    //dequeueCat isEmpty isDogEmpty isCatEmpty

    public class Pet{
        private String type;

        public Pet(String type) {
            this.type=type;
        }

        public String getPetType(){
            return this.type;
        }
    }

    public class Dog extends Pet{
        public Dog(){
            super("dog");
        }
    }

    public class Cat extends Pet{
        public Cat(){
            super("cat");
        }
    }

}
