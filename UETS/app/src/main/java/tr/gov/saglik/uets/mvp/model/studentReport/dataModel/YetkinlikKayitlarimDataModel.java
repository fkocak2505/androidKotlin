package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class YetkinlikKayitlarimDataModel {

    private String demandsDate;
    private String demandsType;
    private String teacherName;
    private int expreinceCount;
    private String explaination;
    private String teamMember;
    private String companyName;
    private String approvedDate;

    public YetkinlikKayitlarimDataModel(String demandsDate, String demandsType, String teacherName, int expreinceCount, String explaination, String teamMember, String companyName, String approvedDate) {
        this.demandsDate = demandsDate;
        this.demandsType = demandsType;
        this.teacherName = teacherName;
        this.expreinceCount = expreinceCount;
        this.explaination = explaination;
        this.teamMember = teamMember;
        this.companyName = companyName;
        this.approvedDate = approvedDate;
    }


    public String getDemandsDate() {
        return demandsDate;
    }

    public void setDemandsDate(String demandsDate) {
        this.demandsDate = demandsDate;
    }

    public String getDemandsType() {
        return demandsType;
    }

    public void setDemandsType(String demandsType) {
        this.demandsType = demandsType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getExpreinceCount() {
        return expreinceCount;
    }

    public void setExpreinceCount(int expreinceCount) {
        this.expreinceCount = expreinceCount;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }
}
