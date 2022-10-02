package Bean;

public class SearchBeans {

    UserBean  userBean = new UserBean();

    CourseBean courseBean = new CourseBean();

    CourseMeisaiBean courseMeisai = new CourseMeisaiBean();

    SubjectBean subjectBean = new SubjectBean();

    ContextBean contextBean = new ContextBean();

    StudyBean studyBean = new StudyBean();



	public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    public CourseMeisaiBean getCourseMeisai() {
        return courseMeisai;
    }

    public void setCourseMeisai(CourseMeisaiBean courseMeisai) {
        this.courseMeisai = courseMeisai;
    }

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }

    public ContextBean getContextBean() {
        return contextBean;
    }

    public void setContextBean(ContextBean contextBean) {
        this.contextBean = contextBean;
    }

	public StudyBean getStudyBean() {
		return studyBean;
	}

	public void setStudyBean(StudyBean studyBean) {
		this.studyBean = studyBean;
	}


}
