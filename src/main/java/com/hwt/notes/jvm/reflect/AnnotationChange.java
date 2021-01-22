package com.hwt.notes.jvm.reflect;

import java.lang.annotation.Annotation;

public interface AnnotationChange {

    boolean changeAnnotationContent(Annotation annotation);

}
