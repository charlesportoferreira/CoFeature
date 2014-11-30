package cofeature;


public class ComparadorFeature implements java.util.Comparator{



	@Override
	public int compare(Object o1, Object o2){
		Feature f1 = (Feature) o1;
		Feature f2 = (Feature) o2;
		if (f1.getInfoGain() > f2.getInfoGain()) {
            return -1;
        }
        if (f1.getInfoGain() < f2.getInfoGain()) {
            return 1;
        }

        return 0;
	}
}