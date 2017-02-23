<?xml version="1.0"?>
<globals>
    <global id="hasNoActionBar" type="boolean" value="false" />
    <global id="parentActivityClass" value="" />

    <global id="baseMvpActivityClass" value="BaseYoloActivity" />
    <global id="baseMvpActivityClassFqcn" value="com.younglive.livestreaming.base.BaseYoloActivity" />
    <global id="baseMvpFragmentClass" value="BaseFragment" />
    <global id="baseMvpFragmentClassFqcn" value="com.younglive.common.base.BaseFragment" />
    <global id="baseMvpPresenterClass" value="BaseRxPresenter" />
    <global id="baseMvpPresenterClassFqcn" value="com.younglive.livestreaming.base.BaseRxPresenter" />
    <global id="appPackageName" value="com.younglive.livestreaming" />

    <global id="simpleLayoutName" value="${layoutName}" />
    <global id="activityClass" value="${featureName}Activity" />
    <global id="excludeMenu" type="boolean" value="true" />
    <global id="featurePackageName" value="${packageName}.${classToResource(featureName)}" />
    <global id="generateActivityTitle" type="boolean" value="false" />
    <#include "../common/common_globals.xml.ftl" />
</globals>
