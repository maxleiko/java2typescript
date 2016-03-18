
package org.kevoree.modeling.java2typescript.json.packagejson;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * The 'scripts' member is an object hash of script commands that are run at various times in the lifecycle of your package. The key is the lifecycle event, and the value is the command to run at that point.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Scripts {

    /**
     * Run BEFORE the package is published (Also run on local npm install without any arguments)
     * 
     */
    @SerializedName("prepublish")
    @Expose
    private String prepublish;
    /**
     * Run AFTER the package is published
     * 
     */
    @SerializedName("publish")
    @Expose
    private String publish;
    /**
     * Run AFTER the package is published
     * 
     */
    @SerializedName("postpublish")
    @Expose
    private String postpublish;
    /**
     * Run BEFORE the package is installed
     * 
     */
    @SerializedName("preinstall")
    @Expose
    private String preinstall;
    /**
     * Run AFTER the package is installed
     * 
     */
    @SerializedName("install")
    @Expose
    private String install;
    /**
     * Run AFTER the package is installed
     * 
     */
    @SerializedName("postinstall")
    @Expose
    private String postinstall;
    /**
     * Run BEFORE the package is uninstalled
     * 
     */
    @SerializedName("preuninstall")
    @Expose
    private String preuninstall;
    /**
     * Run BEFORE the package is uninstalled
     * 
     */
    @SerializedName("uninstall")
    @Expose
    private String uninstall;
    /**
     * Run AFTER the package is uninstalled
     * 
     */
    @SerializedName("postuninstall")
    @Expose
    private String postuninstall;
    /**
     * Run BEFORE bump the package version
     * 
     */
    @SerializedName("preversion")
    @Expose
    private String preversion;
    /**
     * Run BEFORE bump the package version
     * 
     */
    @SerializedName("version")
    @Expose
    private String version;
    /**
     * Run AFTER bump the package version
     * 
     */
    @SerializedName("postversion")
    @Expose
    private String postversion;
    /**
     * Run by the 'npm test' command
     * 
     */
    @SerializedName("pretest")
    @Expose
    private String pretest;
    /**
     * Run by the 'npm test' command
     * 
     */
    @SerializedName("test")
    @Expose
    private String test;
    /**
     * Run by the 'npm test' command
     * 
     */
    @SerializedName("posttest")
    @Expose
    private String posttest;
    /**
     * Run by the 'npm stop' command
     * 
     */
    @SerializedName("prestop")
    @Expose
    private String prestop;
    /**
     * Run by the 'npm stop' command
     * 
     */
    @SerializedName("stop")
    @Expose
    private String stop;
    /**
     * Run by the 'npm stop' command
     * 
     */
    @SerializedName("poststop")
    @Expose
    private String poststop;
    /**
     * Run by the 'npm start' command
     * 
     */
    @SerializedName("prestart")
    @Expose
    private String prestart;
    /**
     * Run by the 'npm start' command
     * 
     */
    @SerializedName("start")
    @Expose
    private String start;
    /**
     * Run by the 'npm start' command
     * 
     */
    @SerializedName("poststart")
    @Expose
    private String poststart;
    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     */
    @SerializedName("prerestart")
    @Expose
    private String prerestart;
    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     */
    @SerializedName("restart")
    @Expose
    private String restart;
    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     */
    @SerializedName("postrestart")
    @Expose
    private String postrestart;

    /**
     * Run BEFORE the package is published (Also run on local npm install without any arguments)
     * 
     * @return
     *     The prepublish
     */
    public String getPrepublish() {
        return prepublish;
    }

    /**
     * Run BEFORE the package is published (Also run on local npm install without any arguments)
     * 
     * @param prepublish
     *     The prepublish
     */
    public void setPrepublish(String prepublish) {
        this.prepublish = prepublish;
    }

    /**
     * Run AFTER the package is published
     * 
     * @return
     *     The publish
     */
    public String getPublish() {
        return publish;
    }

    /**
     * Run AFTER the package is published
     * 
     * @param publish
     *     The publish
     */
    public void setPublish(String publish) {
        this.publish = publish;
    }

    /**
     * Run AFTER the package is published
     * 
     * @return
     *     The postpublish
     */
    public String getPostpublish() {
        return postpublish;
    }

    /**
     * Run AFTER the package is published
     * 
     * @param postpublish
     *     The postpublish
     */
    public void setPostpublish(String postpublish) {
        this.postpublish = postpublish;
    }

    /**
     * Run BEFORE the package is installed
     * 
     * @return
     *     The preinstall
     */
    public String getPreinstall() {
        return preinstall;
    }

    /**
     * Run BEFORE the package is installed
     * 
     * @param preinstall
     *     The preinstall
     */
    public void setPreinstall(String preinstall) {
        this.preinstall = preinstall;
    }

    /**
     * Run AFTER the package is installed
     * 
     * @return
     *     The install
     */
    public String getInstall() {
        return install;
    }

    /**
     * Run AFTER the package is installed
     * 
     * @param install
     *     The install
     */
    public void setInstall(String install) {
        this.install = install;
    }

    /**
     * Run AFTER the package is installed
     * 
     * @return
     *     The postinstall
     */
    public String getPostinstall() {
        return postinstall;
    }

    /**
     * Run AFTER the package is installed
     * 
     * @param postinstall
     *     The postinstall
     */
    public void setPostinstall(String postinstall) {
        this.postinstall = postinstall;
    }

    /**
     * Run BEFORE the package is uninstalled
     * 
     * @return
     *     The preuninstall
     */
    public String getPreuninstall() {
        return preuninstall;
    }

    /**
     * Run BEFORE the package is uninstalled
     * 
     * @param preuninstall
     *     The preuninstall
     */
    public void setPreuninstall(String preuninstall) {
        this.preuninstall = preuninstall;
    }

    /**
     * Run BEFORE the package is uninstalled
     * 
     * @return
     *     The uninstall
     */
    public String getUninstall() {
        return uninstall;
    }

    /**
     * Run BEFORE the package is uninstalled
     * 
     * @param uninstall
     *     The uninstall
     */
    public void setUninstall(String uninstall) {
        this.uninstall = uninstall;
    }

    /**
     * Run AFTER the package is uninstalled
     * 
     * @return
     *     The postuninstall
     */
    public String getPostuninstall() {
        return postuninstall;
    }

    /**
     * Run AFTER the package is uninstalled
     * 
     * @param postuninstall
     *     The postuninstall
     */
    public void setPostuninstall(String postuninstall) {
        this.postuninstall = postuninstall;
    }

    /**
     * Run BEFORE bump the package version
     * 
     * @return
     *     The preversion
     */
    public String getPreversion() {
        return preversion;
    }

    /**
     * Run BEFORE bump the package version
     * 
     * @param preversion
     *     The preversion
     */
    public void setPreversion(String preversion) {
        this.preversion = preversion;
    }

    /**
     * Run BEFORE bump the package version
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Run BEFORE bump the package version
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Run AFTER bump the package version
     * 
     * @return
     *     The postversion
     */
    public String getPostversion() {
        return postversion;
    }

    /**
     * Run AFTER bump the package version
     * 
     * @param postversion
     *     The postversion
     */
    public void setPostversion(String postversion) {
        this.postversion = postversion;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @return
     *     The pretest
     */
    public String getPretest() {
        return pretest;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @param pretest
     *     The pretest
     */
    public void setPretest(String pretest) {
        this.pretest = pretest;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @return
     *     The test
     */
    public String getTest() {
        return test;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @param test
     *     The test
     */
    public void setTest(String test) {
        this.test = test;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @return
     *     The posttest
     */
    public String getPosttest() {
        return posttest;
    }

    /**
     * Run by the 'npm test' command
     * 
     * @param posttest
     *     The posttest
     */
    public void setPosttest(String posttest) {
        this.posttest = posttest;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @return
     *     The prestop
     */
    public String getPrestop() {
        return prestop;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @param prestop
     *     The prestop
     */
    public void setPrestop(String prestop) {
        this.prestop = prestop;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @return
     *     The stop
     */
    public String getStop() {
        return stop;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @param stop
     *     The stop
     */
    public void setStop(String stop) {
        this.stop = stop;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @return
     *     The poststop
     */
    public String getPoststop() {
        return poststop;
    }

    /**
     * Run by the 'npm stop' command
     * 
     * @param poststop
     *     The poststop
     */
    public void setPoststop(String poststop) {
        this.poststop = poststop;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @return
     *     The prestart
     */
    public String getPrestart() {
        return prestart;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @param prestart
     *     The prestart
     */
    public void setPrestart(String prestart) {
        this.prestart = prestart;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @return
     *     The start
     */
    public String getStart() {
        return start;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @param start
     *     The start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @return
     *     The poststart
     */
    public String getPoststart() {
        return poststart;
    }

    /**
     * Run by the 'npm start' command
     * 
     * @param poststart
     *     The poststart
     */
    public void setPoststart(String poststart) {
        this.poststart = poststart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @return
     *     The prerestart
     */
    public String getPrerestart() {
        return prerestart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @param prerestart
     *     The prerestart
     */
    public void setPrerestart(String prerestart) {
        this.prerestart = prerestart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @return
     *     The restart
     */
    public String getRestart() {
        return restart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @param restart
     *     The restart
     */
    public void setRestart(String restart) {
        this.restart = restart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @return
     *     The postrestart
     */
    public String getPostrestart() {
        return postrestart;
    }

    /**
     * Run by the 'npm restart' command. Note: 'npm restart' will run the stop and start scripts if no restart script is provided.
     * 
     * @param postrestart
     *     The postrestart
     */
    public void setPostrestart(String postrestart) {
        this.postrestart = postrestart;
    }

}
