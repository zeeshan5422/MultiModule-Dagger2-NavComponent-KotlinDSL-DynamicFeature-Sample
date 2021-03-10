package com.nfs.ascent.core.ext;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/* ---  Created by akhtarz on 3/10/2020. ---*/
public class VolatileLiveData<T> extends MutableLiveData<T> {

    private AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    public void observe(final @NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {

        mPending.set(false);
        setupOwnerObserver(owner, observer);
    }

    public void observe(final @NonNull LifecycleOwner owner, boolean getDefault, @NonNull final Observer<? super T> observer) {

        mPending.set(getDefault);
        setupOwnerObserver(owner, observer);
    }

    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        mPending.set(false);
        setupObserver(observer);
    }

    public void observeForever(boolean getDefault, @NonNull Observer<? super T> observer) {
        mPending.set(getDefault);
        setupObserver(observer);
    }

    private void setupOwnerObserver(final @NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (mPending.get()) {
                    observer.onChanged(t);
                }
            }
        });
    }

    private void setupObserver(@NonNull final Observer<? super T> observer) {
        super.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (mPending.get()) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void setValue(T value, boolean withCallback) {
        mPending.set(withCallback);
        super.setValue(value);
    }

    @Override
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        mPending.set(true);
        super.postValue(value);
    }

    public void postValue(T value, boolean withCallback) {
        mPending.set(withCallback);
        super.postValue(value);
    }

}
