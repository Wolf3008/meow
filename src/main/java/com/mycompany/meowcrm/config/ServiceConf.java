package com.mycompany.meowcrm.config;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.AriVersion;
import ch.loway.oss.ari4java.tools.ARIException;
import ch.loway.oss.ari4java.tools.http.NettyHttpClient;
import com.mycompany.meowcrm.util.ISessionUser;
import com.mycompany.meowcrm.util.SessionUser;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.meowcrm.service"})
@PropertySource({"classpath:ari_connection.properties"})
public class ServiceConf {

    @Autowired
    private Environment env;

    @Bean
    @Scope(scopeName = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public ISessionUser geSessionUser() {
        return new SessionUser();
    }

    @Bean(name = "ari")
    public ARI getAri() {
        final ARI ari = new ARI();
        try {
            NettyHttpClient hc = new NettyHttpClient();
            hc.initialize(env.getProperty("ari.base_url"), env.getProperty("ari.user_name"), env.getProperty("ari.password"));
            ari.setHttpClient(hc);
            ari.setWsClient(hc);
            ari.setVersion(AriVersion.ARI_1_10_0);
            Logger.getLogger(ServiceConf.class.getName()).log(Level.SEVERE, "\nAster version: {0}", ari.asterisk().getInfo("").getSystem().getVersion());
        } catch (URISyntaxException | ARIException ex) {
            Logger.getLogger(ServiceConf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServiceConf.class.getName()).log(Level.SEVERE, null, ex);
            //NOP
        }
        return ari;
    }
}
