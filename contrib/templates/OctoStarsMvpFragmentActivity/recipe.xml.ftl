<?xml version="1.0"?>
<recipe>
    <#include "root/recipe_manifest.xml.ftl" />

<#if !useFragment>
    <#include "../common/recipe_simple.xml.ftl" />
</#if>

    <instantiate from="../common/root/res/layout/simple.xml.ftl"
                     to="${escapeXmlAttribute(resOut)}/layout/${fragmentLayoutName}.xml" />

    <instantiate from="root/src/app_package/MvpFeatureActivity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${activityClass}.java" />

    <instantiate from="root/src/app_package/MvpFeatureComponent.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/di/${featureName}Component.java" />

    <instantiate from="root/src/app_package/MvpFeatureModule.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/di/${featureName}Module.java" />

    <instantiate from="root/src/app_package/MvpFeatureView.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/mvp/${featureName}View.java" />

    <instantiate from="root/src/app_package/MvpFeatureFragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${fragmentName}.java" />

    <instantiate from="root/src/app_package/MvpFeaturePresenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/mvp/${featureName}Presenter.java" />

    <instantiate from="root/src/app_package/MvpFeaturePresenterImpl.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${featureName}PresenterImpl.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${activityClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${fragmentName}.java" />
</recipe>
