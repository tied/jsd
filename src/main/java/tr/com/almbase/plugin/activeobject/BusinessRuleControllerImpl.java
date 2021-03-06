package tr.com.almbase.plugin.activeobject;

import com.atlassian.activeobjects.external.ActiveObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.almbase.plugin.util.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kivanc.ahat@almbase.com
 */

public class BusinessRuleControllerImpl implements BusinessRuleController{

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleControllerImpl.class);
    private ActiveObjects activeObjects;

    public BusinessRuleControllerImpl(ActiveObjects activeObjects)
    {
        this.activeObjects = activeObjects;
    }

    public BusinessRule getRecordFromAOTableByUserName(String userName) {
        BusinessRule businessRule = null;
        try {
            BusinessRule[] tempBusinessRule = activeObjects.find(BusinessRule.class, "USER_NAME = ?", userName);
            if (null != tempBusinessRule && tempBusinessRule.length > 0)
                businessRule = tempBusinessRule[0];
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRule;
    }

    public BusinessRule getRecordFromAOTableById(String businessRuleId) {
        BusinessRule businessRule = null;
        try {
            BusinessRule[] tempBusinessRule = activeObjects.find(BusinessRule.class, "ID = ?", Integer.parseInt(businessRuleId));
            if (null != tempBusinessRule && tempBusinessRule.length > 0)
                businessRule = tempBusinessRule[0];
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRule;
    }

    public BusinessRule[] getRecordFromAOTableByCategoryId(String categoryId) {
        BusinessRule[] businessRules = null;
        try {
            businessRules = activeObjects.find(BusinessRule.class, "CATEGORY_ID = ?", categoryId);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule[] getRecordFromAOTableBySubCategoryId(String subCategoryId) {
        BusinessRule[] businessRules = null;
        try {
            businessRules = activeObjects.find(BusinessRule.class, "SUB_CATEGORY_ID = ?", subCategoryId);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule [] getRecordFromAOTableByCategoryItemId(String categoryItemId) {
        BusinessRule [] businessRules = null;
        try {
            businessRules = activeObjects.find(BusinessRule.class, "CATEGORY_ITEM_ID = ?", categoryItemId);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule [] getRecordFromAOTableByCategoryComponentId(String categoryComponentId) {
        BusinessRule [] businessRules = null;
        try {
            businessRules = activeObjects.find(BusinessRule.class, "CATEGORY_COMPONENT_ID = ?", categoryComponentId);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule [] getAllRecordFromAOTableByIssueType(String issueType, String categoryId, String subCategoryId, String categoryItemId, String categoryComponentId) {
        BusinessRule [] businessRules = null;
        try {
            if (null != issueType && !issueType.equalsIgnoreCase("")) {
                if (null != categoryComponentId && !categoryComponentId.equalsIgnoreCase("")) {
                    businessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_COMPONENT_ID = ?", issueType, categoryComponentId);
                    if (null == businessRules || (businessRules != null && businessRules.length == 0)) {
                        log.debug("Issue Type : " + issueType + " with Category Component " + categoryComponentId +" Business rule is null!");
                    } else {
                        log.debug("Issue Type : " + issueType + " with Category Component " + categoryComponentId +" Business rule length is " + businessRules.length);
                    }
                }
                if (null == businessRules && null != categoryItemId && !categoryItemId.equalsIgnoreCase("")) {
                    businessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ITEM_ID = ?", issueType, categoryItemId);
                    if (null == businessRules || (businessRules != null && businessRules.length == 0)) {
                        log.debug("Issue Type : " + issueType + " with Category Item " + categoryItemId +" Business rule is null!");
                    } else {
                        log.debug("Issue Type : " + issueType + " with Category Item " + categoryItemId +" Business rule length is " + businessRules.length);
                    }
                }
                if (null == businessRules && null != subCategoryId && !subCategoryId.equalsIgnoreCase("")) {
                    businessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND SUB_CATEGORY_ID = ?", issueType, subCategoryId);
                    if (null == businessRules || (businessRules != null && businessRules.length == 0)) {
                        log.debug("Issue Type : " + issueType + " with Sub Category " + subCategoryId +" Business rule is null!");
                    } else {
                        log.debug("Issue Type : " + issueType + " with Sub Category " + subCategoryId +" Business rule length is " + businessRules.length);
                    }
                }
                if (null == businessRules && null != categoryId && !categoryId.equalsIgnoreCase("")) {
                    businessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ID = ?", issueType, categoryId);
                    if (null == businessRules || (businessRules != null && businessRules.length == 0)) {
                        log.debug("Issue Type : " + issueType + " with Category " + categoryId +" Business rule is null!");
                    } else {
                        log.debug("Issue Type : " + issueType + " with Category " + categoryId +" Business rule length is " + businessRules.length);
                    }
                }
            }
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule getRecordFromAOTableByIssueType(String issueType, String categoryId, String subCategoryId, String categoryItemId, String categoryComponentId) {
        BusinessRule businessRule = null;
        try {
            if (null != issueType && !issueType.equalsIgnoreCase("")) {
                Utils.printDebug("getRecordFromAOTableByIssueType : issueType is not null." );
                if (null != categoryComponentId && !categoryComponentId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryComponentId is not null." + categoryComponentId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_COMPONENT_ID = ?", issueType, categoryComponentId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryComponentId().equalsIgnoreCase(categoryComponentId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryComponentId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                }
                if (null == businessRule
                        && (null != categoryItemId && !categoryItemId.equalsIgnoreCase(""))) {
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryItemId is not null." + categoryItemId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ITEM_ID = ?", issueType, categoryItemId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryItemId().equalsIgnoreCase(categoryItemId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryItemId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                }
                if (null == businessRule
                        && (null != subCategoryId && !subCategoryId.equalsIgnoreCase(""))) {
                    Utils.printDebug("getRecordFromAOTableByIssueType : subCategoryId is not null." + subCategoryId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND SUB_CATEGORY_ID = ?", issueType, subCategoryId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getSubCategoryId().equalsIgnoreCase(subCategoryId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByIssueType : subCategoryId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                }
                if (null == businessRule
                        && null != categoryId && !categoryId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryId is not null." + categoryId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ID = ?", issueType, categoryId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryId().equalsIgnoreCase(categoryId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByIssueType : categoryId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                }
            }
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRule;
    }

    public BusinessRule getRecordFromAOTableByAllParameters(String issueType, String categoryId, String subCategoryId, String categoryItemId, String categoryComponentId) {
        BusinessRule businessRule = null;
        try {
            if (null != issueType && !issueType.equalsIgnoreCase("")) {
                Utils.printDebug("getRecordFromAOTableByAllParameters : issueType is not null." );
                if (null != categoryComponentId && !categoryComponentId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryComponentId is not null." + categoryComponentId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_COMPONENT_ID = ?", issueType, categoryComponentId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryComponentId().equalsIgnoreCase(categoryComponentId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryComponentId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                } else if (null != categoryItemId && !categoryItemId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryItemId is not null." + categoryItemId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ITEM_ID = ?", issueType, categoryItemId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryItemId().equalsIgnoreCase(categoryItemId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryItemId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                } else if (null != subCategoryId && !subCategoryId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByAllParameters : subCategoryId is not null." + subCategoryId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND SUB_CATEGORY_ID = ?", issueType, subCategoryId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getSubCategoryId().equalsIgnoreCase(subCategoryId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByAllParameters : subCategoryId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                } else if (null != categoryId && !categoryId.equalsIgnoreCase("")) {
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryId is not null." + categoryId);
                    BusinessRule [] tempBusinessRules = activeObjects.find(BusinessRule.class, "ISSUE_TYPE = ? AND CATEGORY_ID = ?", issueType, categoryId);
                    List<String> userNames = new ArrayList<>();
                    for (BusinessRule br : tempBusinessRules) {
                        if (br.getCategoryId().equalsIgnoreCase(categoryId)) {
                            if (null != br.getUserName() && !br.getUserName().trim().equalsIgnoreCase("")) {
                                userNames.add(br.getUserName().trim());
                            }
                        }
                    }
                    Utils.printDebug("getRecordFromAOTableByAllParameters : categoryId : userNames : " + Arrays.toString(userNames.toArray()));
                    if (userNames.size() == 1) {
                        businessRule = tempBusinessRules[0];
                    }
                }
            }
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRule;
    }

    public BusinessRule[] getAllEntriesFromAOTable() {
        BusinessRule[] businessRules = null;
        try {
            businessRules = activeObjects.find(BusinessRule.class);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            log.error("Couldn't find any record!");
        }
        return businessRules;
    }

    public BusinessRule createRecordInAOTable(BusinessRuleObject businessRuleObject) {
        BusinessRule returnBusinessRule = null;
        try {
            BusinessRule foundAO = null;
             if (null != businessRuleObject.getIssueType() && !businessRuleObject.getIssueType().equalsIgnoreCase("")) {
                 BusinessRule businessRule = getRecordFromAOTableByAllParameters(businessRuleObject.getIssueType(), businessRuleObject.getCategoryId(), businessRuleObject.getSubCategoryId(), businessRuleObject.getCategoryItemId(), businessRuleObject.getCategoryComponentId());
                 if (null != businessRule) {
                     foundAO = businessRule;
                 }
            } else if (null != businessRuleObject.getCategoryComponentId() && !businessRuleObject.getCategoryComponentId().equalsIgnoreCase("")) {
                BusinessRule [] businessRules = getRecordFromAOTableByCategoryComponentId(businessRuleObject.getCategoryComponentId());
                if (null != businessRules && businessRules.length == 1) {
                    foundAO = businessRules[0];
                }
            } else if (null != businessRuleObject.getCategoryItemId() && !businessRuleObject.getCategoryItemId().equalsIgnoreCase("")) {
                BusinessRule [] businessRules = getRecordFromAOTableByCategoryItemId(businessRuleObject.getCategoryItemId());
                if (null != businessRules && businessRules.length == 1) {
                    foundAO = businessRules[0];
                }
            } else if (null != businessRuleObject.getSubCategoryId() && !businessRuleObject.getSubCategoryId().equalsIgnoreCase("")) {
                BusinessRule [] businessRules = getRecordFromAOTableBySubCategoryId(businessRuleObject.getSubCategoryId());
                if (null != businessRules && businessRules.length == 1) {
                    foundAO = businessRules[0];
                }
            } else if (null != businessRuleObject.getCategoryId() && !businessRuleObject.getCategoryId().equalsIgnoreCase("")) {
                BusinessRule [] businessRules = getRecordFromAOTableByCategoryId(businessRuleObject.getCategoryId());
                if (null != businessRules && businessRules.length == 1) {
                    foundAO = businessRules[0];
                }
            }


            if (foundAO != null)
            {
                deleteRecordFromAOTable(foundAO);

                BusinessRule businessRule = activeObjects.create(BusinessRule.class);
                if (businessRule != null)
                {
                    returnBusinessRule = setAOValuesAndReturnAsObject(businessRuleObject, businessRule);
                } else {
                    log.error("An error occured while creating empty object!");
                }
            } else {
                log.debug("New setting!");
                BusinessRule businessRule = activeObjects.create(BusinessRule.class);
                if (businessRule != null)
                {
                    returnBusinessRule = setAOValuesAndReturnAsObject(businessRuleObject, businessRule);
                } else {
                    log.error("An error occured while creating empty object!");
                }
            }
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
        }
        return returnBusinessRule;
    }

    public BusinessRule updateRecordInAOTable(BusinessRule businessRule, BusinessRuleObject businessRuleObject) {
        try {
            if (businessRule != null) {
                businessRule.setSubCategoryId(businessRuleObject.getSubCategoryId());
                businessRule.setCategoryItemId(businessRuleObject.getCategoryItemId());
                businessRule.setUserName(businessRuleObject.getUserName());
                businessRule.setIssueType(businessRuleObject.getIssueType());
                businessRule.save();
            } else {
                log.error("An error occured while creating empty object!");
            }
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
        }
        return businessRule;
    }

    public void deleteRecordFromAOTable(BusinessRule businessRule) {
        try {
            activeObjects.delete(businessRule);
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
        }
    }

    private BusinessRule setAOValuesAndReturnAsObject(BusinessRuleObject businessRuleObject, BusinessRule businessRuleRecord) {
        try {
            businessRuleRecord.setCategoryId(businessRuleObject.getCategoryId());
            businessRuleRecord.setSubCategoryId(businessRuleObject.getSubCategoryId());
            businessRuleRecord.setCategoryItemId(businessRuleObject.getCategoryItemId());
            businessRuleRecord.setUserName(businessRuleObject.getUserName());
            businessRuleRecord.setIssueType(businessRuleObject.getIssueType());
            businessRuleRecord.setCategoryComponentId(businessRuleObject.getCategoryComponentId());
            businessRuleRecord.save();
        } catch (Exception e) {
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
            businessRuleRecord = null;
        }
        return businessRuleRecord;
    }
}
