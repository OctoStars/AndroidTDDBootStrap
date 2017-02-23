<?xml version="1.0"?>
<recipe>
    <#include "root/recipe_manifest.xml.ftl" />

    <instantiate from="../common/root/res/layout/simple.xml.ftl"
                     to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />

    <instantiate from="root/src/app_package/MvpFeatureActivity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${activityClass}.java" />

    <instantiate from="root/src/app_package/MvpFeatureComponent.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${featureName}Component.java" />

    <instantiate from="root/src/app_package/MvpFeatureView.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${featureName}View.java" />

    <instantiate from="root/src/app_package/MvpFeaturePresenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${featureName}Presenter.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${activityClass}.java" />
</recipe>
