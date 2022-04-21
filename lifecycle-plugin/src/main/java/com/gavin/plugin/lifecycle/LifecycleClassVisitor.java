package com.gavin.plugin.lifecycle;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gavin
 * @date 2019/2/18
 * lifecycle class visitor
 */
public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //System.out.println("LifecycleClassVisitor : visit -----> started ：" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //匹配FragmentActivity
//        if ("android/support/v4/app/FragmentActivity".equals(this.mClassName)  && !"<init>".equals(name)) {
//            if ("onCreate".equals(name)) {
//                //处理onCreate
//                System.out.println("LifecycleClassVisitor : change method ---- " + name);
//                return new LifecycleOnCreateMethodVisitor(mv);
//            } else if ("onDestroy".equals(name)) {
//                //处理onDestroy
//                System.out.println("LifecycleClassVisitor : change method ---- " + name);
//                return new LifecycleCommonMethodVisitor(mv);
//            }
//            else {
////                System.out.println("LifecycleClassVisitor : change method ---- " + name);
////                return new LifecycleOnDestroyMethodVisitor(mv);
//
//                System.out.println("LifecycleClassVisitor  asmdemo FragmentActivity: change method ---- " + name);
//                LifecycleCommonMethodVisitor lo = new LifecycleCommonMethodVisitor(mv);
//                lo.setFunctionName(name);
//                return lo;
//            }
//        }

        System.out.println("this.mClassName = " + this.mClassName);

        if ("androidx/fragment/app/FragmentActivity".equals(this.mClassName) && !"<init>".equals(name)) {
            System.out.println("LifecycleClassVisitor FragmentActivity: change method ---- " + name);
            LifecycleCommonMethodVisitor lo = new LifecycleCommonMethodVisitor(mv);
            lo.setFunctionName(name);
            return lo;
        } else if ("com/gavin/asmdemo/MainActivity".equals(this.mClassName) && !"<init>".equals(name)) {
            System.out.println("LifecycleClassVisitor MainActivity: change method ---- " + name);
            LifecycleCommonMethodVisitor lo = new LifecycleCommonMethodVisitor(mv);
            lo.setFunctionName(name);
            return lo;
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
