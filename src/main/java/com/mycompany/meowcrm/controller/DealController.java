package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.model.deal.DealState;
import com.mycompany.meowcrm.model.deal.DealType;
import com.mycompany.meowcrm.model.deal.ThingType;
import com.mycompany.meowcrm.service.IDealService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deal")
public class DealController {

    @Autowired
    private IDealService dealService;

    @RequestMapping(method = RequestMethod.PUT)
    public long addDeal(@RequestBody Deal deal) {
        deal.getThings().stream().forEach((t) -> {
            t.setDeal(deal);
        });
        return dealService.addDeal(deal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public long update(@RequestBody Deal deal) {
        return dealService.update(deal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void del(@PathVariable long id) {
        dealService.del(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Deal get(@PathVariable long id) {
        return dealService.get(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public Map filterDeals(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int items,
            @RequestParam(required = false) Integer[] type,
            @RequestParam(required = false) Integer[] state,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Long[] managers) {
        return dealService.filterDeals(page, items, type, state, filter, managers);
    }

    //deal types
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public long addDealType(@RequestBody DealType dealType) {
        return dealService.addDealType(dealType);
    }

    @RequestMapping(value = "/type/{id}", method = RequestMethod.POST)
    public long updateDealType(@RequestBody DealType dealType) {
        return dealService.updateDealType(dealType);
    }

    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public void delDealType(@PathVariable int id) {
        dealService.delDealType(id);
    }

    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public List getDealTypes() {
        return dealService.getDealTypes();
    }

    //deal states
    @RequestMapping(value = "/state", method = RequestMethod.PUT)
    public long addDealState(@RequestBody DealState dealState) {
        return dealService.addDealState(dealState);
    }

    @RequestMapping(value = "/state/{id}", method = RequestMethod.POST)
    public long updateDealState(@RequestBody DealState dealState) {
        return dealService.updateDealState(dealState);
    }

    @RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
    public void delDealState(@PathVariable int id) {
        dealService.delDealState(id);
    }

    @RequestMapping(value = "/state/list", method = RequestMethod.GET)
    public List getDealStates() {
        return dealService.getDealStates();
    }

    //deal thing
    @RequestMapping(value = "/thing/type", method = RequestMethod.PUT)
    public long addThingType(@RequestBody ThingType thingType) {
        return dealService.addThingType(thingType);
    }

    @RequestMapping(value = "/thing/type/{id}", method = RequestMethod.POST)
    public long updateThingType(@RequestBody ThingType thingType) {
        return dealService.updateThingType(thingType);
    }

    @RequestMapping(value = "/thing/type/{id}", method = RequestMethod.DELETE)
    public void delThingType(@PathVariable int id) {
        dealService.delThingType(id);
    }

    @RequestMapping(value = "/thing/type/list", method = RequestMethod.GET)
    public List getThingTypes(@RequestParam(required = false) Integer dealType) {
        return dealService.getThingTypes(dealType);
    }
}
