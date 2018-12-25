package studentCoursesBackup.myTree;

public interface SubjectI
{
	public void register(Node node);
	public void unregister(Node node);
	public void notifyAllObservers(String course, UpdateLevel level);
}