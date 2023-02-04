package com.example.nextstop.models;

public class AgentModelClass {

    private String agentname,agentphone,agentemail,agentnid;

    public AgentModelClass(String agentname, String agentphone, String agentemail, String agentnid) {
        this.agentname = agentname;
        this.agentphone = agentphone;
        this.agentemail = agentemail;
        this.agentnid = agentnid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getAgentphone() {
        return agentphone;
    }

    public void setAgentphone(String agentphone) {
        this.agentphone = agentphone;
    }

    public String getAgentemail() {
        return agentemail;
    }

    public void setAgentemail(String agentemail) {
        this.agentemail = agentemail;
    }

    public String getAgentnid() {
        return agentnid;
    }

    public void setAgentnid(String agentnid) {
        this.agentnid = agentnid;
    }
}
