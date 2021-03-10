package com.nfs.ascent.core.ext;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.nfs.ascent.core.utils.NfsLogUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/* ---  Created by akhtarz on 3/6/2020. ---*/
public class AppLiveData<T> extends MutableLiveData<T> {

    private final AtomicBoolean byPass = new AtomicBoolean(false);

    private LifecycleOwner owner;
    @NonNull
    private Observer<? super T> observer;

    public AppLiveData() {
        byPass.set(false);
    }

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {

        NfsLogUtils.e("AppLiveData >> Observer Sub:" + getValue());

        super.observe(owner, observer);
    }

    @MainThread
    public void setValue(T value) {
        super.setValue(value);

        if (this.byPass.get()) {
            NfsLogUtils.e("AppLiveData >> Observer By pass mode is fulfilled,:::" + this.byPass.get());
            this.byPass.set(false);
            observe(owner, observer);
        }
    }

    @MainThread
    public void setValue(T value, boolean withCallBack) {

        if (!withCallBack) {
            removeObserver(observer);
        } else {
            if (null != owner && null != observer && !hasObservers()) {
                addObserver(owner, observer);
            }
        }
        setValue(value);
    }

    public void addObserver(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        addObserver(owner, observer, false);
    }

    public void addObserver(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer, boolean byPassMode) {
        this.owner = owner;
        this.observer = observer;
        byPass.set(byPassMode);
        if (!byPass.get()) {
            NfsLogUtils.e("AppLiveData >> Observer by pass Mode is disabled:");
            observe(this.owner, this.observer);
        } else {
            NfsLogUtils.e("AppLiveData >> Observer by pass Mode is enabled:");
        }
    }

}
