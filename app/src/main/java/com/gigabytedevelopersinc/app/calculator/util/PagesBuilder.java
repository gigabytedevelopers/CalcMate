/*
 * Calculator Begins
 * Copyright (C) 2017  Adithya J
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.gigabytedevelopersinc.app.calculator.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Iterator;

public class PagesBuilder implements Iterable<PagesBuilder.Page> {

    private final ArrayList<Page> mPages;

    public PagesBuilder(int expectedSize) {
        mPages = new ArrayList<>(expectedSize);
    }

    public void add(@NonNull Page page) {
        mPages.add(page);
    }

    public Page get(int index) {
        return mPages.get(index);
    }

    public int size() {
        return mPages.size();
    }

    @Override
    public Iterator<Page> iterator() {
        return mPages.iterator();
    }

    public static class Page {

        public final Drawable iconRes;
        @NonNull
        public final Fragment fragment;

        public Page(Drawable iconRes, @NonNull Fragment fragment) {
            this.iconRes = iconRes;
            this.fragment = fragment;
        }
    }
}