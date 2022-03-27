package com.app.voiceclassification.mvvm.capsules.response;

import com.app.voiceclassification.mvvm.capsules.Super;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryPojo extends Super {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("mdvp_fo")
    @Expose
    private Integer mdvpFo;
    @SerializedName("mdvp_fhi")
    @Expose
    private Integer mdvpFhi;
    @SerializedName("mdvp_flo")
    @Expose
    private Integer mdvpFlo;
    @SerializedName("mdvp_shimmer")
    @Expose
    private Integer mdvpShimmer;
    @SerializedName("mdvp_jitter")
    @Expose
    private Integer mdvpJitter;
    @SerializedName("rpde")
    @Expose
    private Integer rpde;
    @SerializedName("d2")
    @Expose
    private Integer d2;
    @SerializedName("nhr")
    @Expose
    private Integer nhr;
    @SerializedName("hnr")
    @Expose
    private Integer hnr;
    @SerializedName("dfa")
    @Expose
    private Integer dfa;
    @SerializedName("ppe")
    @Expose
    private Integer ppe;
    @SerializedName("spread1")
    @Expose
    private Integer spread1;
    @SerializedName("spread2")
    @Expose
    private Integer spread2;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Integer getMdvpFo() {
        return mdvpFo;
    }

    public void setMdvpFo(Integer mdvpFo) {
        this.mdvpFo = mdvpFo;
    }

    public Integer getMdvpFhi() {
        return mdvpFhi;
    }

    public void setMdvpFhi(Integer mdvpFhi) {
        this.mdvpFhi = mdvpFhi;
    }

    public Integer getMdvpFlo() {
        return mdvpFlo;
    }

    public void setMdvpFlo(Integer mdvpFlo) {
        this.mdvpFlo = mdvpFlo;
    }

    public Integer getMdvpShimmer() {
        return mdvpShimmer;
    }

    public void setMdvpShimmer(Integer mdvpShimmer) {
        this.mdvpShimmer = mdvpShimmer;
    }

    public Integer getMdvpJitter() {
        return mdvpJitter;
    }

    public void setMdvpJitter(Integer mdvpJitter) {
        this.mdvpJitter = mdvpJitter;
    }

    public Integer getRpde() {
        return rpde;
    }

    public void setRpde(Integer rpde) {
        this.rpde = rpde;
    }

    public Integer getD2() {
        return d2;
    }

    public void setD2(Integer d2) {
        this.d2 = d2;
    }

    public Integer getNhr() {
        return nhr;
    }

    public void setNhr(Integer nhr) {
        this.nhr = nhr;
    }

    public Integer getHnr() {
        return hnr;
    }

    public void setHnr(Integer hnr) {
        this.hnr = hnr;
    }

    public Integer getDfa() {
        return dfa;
    }

    public void setDfa(Integer dfa) {
        this.dfa = dfa;
    }

    public Integer getPpe() {
        return ppe;
    }

    public void setPpe(Integer ppe) {
        this.ppe = ppe;
    }

    public Integer getSpread1() {
        return spread1;
    }

    public void setSpread1(Integer spread1) {
        this.spread1 = spread1;
    }

    public Integer getSpread2() {
        return spread2;
    }

    public void setSpread2(Integer spread2) {
        this.spread2 = spread2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "id = " + id +
                "\naudio = '" + audio +
                "\nmdvpFo = " + mdvpFo +
                "\nmdvpFhi = " + mdvpFhi +
                "\nmdvpFlo = " + mdvpFlo +
                "\nmdvpShimmer = " + mdvpShimmer +
                "\nmdvpJitter = " + mdvpJitter +
                "\nrpde = " + rpde +
                "\nd2 = " + d2 +
                "\nnhr = " + nhr +
                "\nhnr = " + hnr +
                "\ndfa = " + dfa +
                "\nppe = " + ppe +
                "\nspread1 = " + spread1 +
                "\nspread2 = " + spread2 +
                "\nstatus = " + status +
                "\nisActive = " + isActive +
                "\ncreatedOn = '" + createdOn;
    }
}
