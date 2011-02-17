package org.kyoto_gtug.savetheworld.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.kyoto_gtug.savetheworld.domain.Help;
import org.springframework.orm.jdo.support.JdoDaoSupport;

public class HelpDao extends JdoDaoSupport {

    public List<Help> getRecentHelps() {
        Collection<?> result = getJdoTemplate().find(Help.class, null, "id DESC");
        List<Help> helps = new LinkedList<Help>();
        for (Object r : result) {
            helps.add((Help)r);
        }
        return helps;
    }

    public void save(Help help) {
        getJdoTemplate().makePersistent(help);
    }
}
