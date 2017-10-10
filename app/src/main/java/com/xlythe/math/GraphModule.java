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

package com.xlythe.math;

import android.os.AsyncTask;

import org.javia.arity.SyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GraphModule extends Module {
    private static final String X = "X";
    private static final String Y = "Y";
    private float mMinY;
    private float mMaxY;
    private float mMinX;
    private float mMaxX;
    private float mZoomLevel = 1f;

    public GraphModule(Solver solver) {
        super(solver);
    }

    public void setRange(float min, float max) {
        mMinY = min;
        mMaxY = max;
    }

    public void setDomain(float min, float max) {
        mMinX = min;
        mMaxX = max;
    }

    public void setZoomLevel(float level) {
        mZoomLevel = level;
    }

    /**
     * Given a function, updateGraph will attempt to build a list of points that can be graphed.
     */
    public AsyncTask updateGraph(String text, OnGraphUpdatedListener l) {
        boolean endsWithOperator = text.length() != 0 &&
                (Solver.isOperator(text.charAt(text.length() - 1)) || text.endsWith("("));
        boolean containsMatrices = getSolver().displayContainsMatrices(text);
        boolean domainNotSet = mMinX == mMaxX;
        if (endsWithOperator || containsMatrices || domainNotSet) {
            return null;
        }

        GraphTask newTask = new GraphTask(getSolver(), mMinY, mMaxY, mMinX, mMaxX, mZoomLevel, l);
        newTask.execute(text);
        return newTask;
    }

    public interface OnGraphUpdatedListener {
        void onGraphUpdated(List<Point> result);
    }

    class GraphTask extends AsyncTask<String, String, List<Point>> {
        private final Solver mSolver;
        private final OnGraphUpdatedListener mListener;
        private final float mMinY;
        private final float mMaxY;
        private final float mMinX;
        private final float mMaxX;
        private final float mZoomLevel;

        public GraphTask(Solver solver, float minY, float maxY, float minX, float maxX,
                         float zoomLevel, OnGraphUpdatedListener l) {
            mSolver = solver;
            mListener = l;
            mMinY = minY;
            mMaxY = maxY;
            mMinX = minX;
            mMaxX = maxX;
            mZoomLevel = zoomLevel;
        }

        @Override
        protected List<Point> doInBackground(String... eq) {
            String[] equations = eq[0].split("=");
            try {
                if (equations.length >= 2) {
                    String leftEquation = mSolver.getBaseModule().changeBase(equations[0],
                            mSolver.getBaseModule().getBase(), Base.DECIMAL);
                    String rightEquation = mSolver.getBaseModule().changeBase(equations[1],
                            mSolver.getBaseModule().getBase(), Base.DECIMAL);
                    return graph(leftEquation, rightEquation);
                } else {
                    String equation = mSolver.getBaseModule().changeBase(eq[0],
                            mSolver.getBaseModule().getBase(), Base.DECIMAL);
                    return graph(equation);
                }
            } catch (SyntaxException e) {
                cancel(true);
                return null;
            }
        }

        public List<Point> graph(String equation) {
            final LinkedList<Point> series = new LinkedList<Point>();
            mSolver.pushFrame();

            final float delta = 0.1f * mZoomLevel;
            for (float x = mMinX; x <= mMaxX; x += delta) {
                if (isCancelled()) {
                    return null;
                }

                try {
                    mSolver.define(X, x);
                    float y = (float) mSolver.eval(equation);
                    series.add(new Point(x, y));
                } catch (SyntaxException e) {
                }
            }
            mSolver.popFrame();

            return Collections.unmodifiableList(series);
        }

        public List<Point> graph(String leftEquation, String rightEquation) {
            List<Point> series = new LinkedList<>();
            mSolver.pushFrame();

            final float delta = 0.1f * mZoomLevel;
            if (leftEquation.equals(Y) && !rightEquation.contains(Y)) {
                for (float x = mMinX; x <= mMaxX; x += delta) {
                    if (isCancelled()) {
                        return null;
                    }

                    try {
                        mSolver.define(X, x);
                        float y = (float) mSolver.eval(rightEquation);
                        series.add(new Point(x, y));
                    } catch (SyntaxException e) {
                    }
                }
            } else if (leftEquation.equals(X) && !rightEquation.contains(X)) {
                for (float y = mMinY; y <= mMaxY; y += delta) {
                    if (isCancelled()) {
                        return null;
                    }

                    try {
                        mSolver.define(Y, y);
                        float x = (float) mSolver.eval(rightEquation);
                        series.add(new Point(x, y));
                    } catch (SyntaxException e) {
                    }
                }
            } else if (rightEquation.equals(Y) && !leftEquation.contains(Y)) {
                for (float x = mMinX; x <= mMaxX; x += delta) {
                    if (isCancelled()) {
                        return null;
                    }

                    try {
                        mSolver.define(X, x);
                        float y = (float) mSolver.eval(leftEquation);
                        series.add(new Point(x, y));
                    } catch (SyntaxException e) {
                    }
                }
            } else if (rightEquation.equals(X) && !leftEquation.contains(X)) {
                for (float y = mMinY; y <= mMaxY; y += delta) {
                    if (isCancelled()) {
                        return null;
                    }

                    try {
                        mSolver.define(Y, y);
                        float x = (float) mSolver.eval(leftEquation);
                        series.add(new Point(x, y));
                    } catch (SyntaxException e) {
                    }
                }
            } else {
                for (float x = mMinX; x <= mMaxX; x += 0.1f * mZoomLevel) {
                    for (float y = mMaxY; y >= mMinY; y -= 0.1f * mZoomLevel) {
                        if (isCancelled()) {
                            return null;
                        }

                        try {
                            mSolver.define(X, x);
                            mSolver.define(Y, y);
                            float leftSide = (float) mSolver.eval(leftEquation);
                            float rightSide = (float) mSolver.eval(rightEquation);

                            // Should be close to 0 if they're similar
                            float condensedResult = Math.abs(leftSide - rightSide);
                            if (condensedResult < 0.02f) {
                                series.add(new Point(x, y));
                            }
                        } catch (SyntaxException e) {
                        }
                    }
                }

                series = sort(series);
            }

            mSolver.popFrame();

            return Collections.unmodifiableList(series);
        }

        private List<Point> sort(List<Point> data) {
            List<Point> sorted = new ArrayList<>(data.size());
            Point key = null;
            while (!data.isEmpty()) {
                if (key == null) {
                    key = data.get(0);
                    data.remove(0);
                    sorted.add(key);
                }
                key = findClosestPoint(key, data);
                data.remove(key);
                sorted.add(key);
            }
            return sorted;
        }

        private Point findClosestPoint(Point key, List<Point> data) {
            Point closestPoint = null;
            for (Point p : data) {
                if (closestPoint == null) closestPoint = p;
                if (getDistance(key, p) < getDistance(key, closestPoint)) closestPoint = p;
            }
            return closestPoint;
        }

        private double getDistance(Point a, Point b) {
            return Math.sqrt(square(a.getX() - b.getX()) + square(a.getY() - b.getY()));
        }

        private double square(double val) {
            return val * val;
        }

        @Override
        protected void onPostExecute(List<Point> result) {
            mListener.onGraphUpdated(result);
        }
    }
}
