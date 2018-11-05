package ir.taghizadeh.rxjava2_operators.utils;

import ir.taghizadeh.rxjava2_operators.combining.CombineLatestObservable;
import ir.taghizadeh.rxjava2_operators.combining.ConcatObservable;
import ir.taghizadeh.rxjava2_operators.combining.JoinObservable;
import ir.taghizadeh.rxjava2_operators.combining.MergeObservable;
import ir.taghizadeh.rxjava2_operators.combining.ZipObservable;
import ir.taghizadeh.rxjava2_operators.conditional.AllObservable;
import ir.taghizadeh.rxjava2_operators.conditional.AmbObservable;
import ir.taghizadeh.rxjava2_operators.conditional.ContainsObservable;
import ir.taghizadeh.rxjava2_operators.conditional.DefaultIfEmptyObservable;
import ir.taghizadeh.rxjava2_operators.conditional.SequenceEqualObservable;
import ir.taghizadeh.rxjava2_operators.conditional.SkipUntilObservable;
import ir.taghizadeh.rxjava2_operators.conditional.SkipWhileObservable;
import ir.taghizadeh.rxjava2_operators.conditional.TakeUntilObservable;
import ir.taghizadeh.rxjava2_operators.conditional.TakeWhileObservable;
import ir.taghizadeh.rxjava2_operators.creating.CreateObservable;
import ir.taghizadeh.rxjava2_operators.creating.DeferObservable;
import ir.taghizadeh.rxjava2_operators.creating.FromObservable;
import ir.taghizadeh.rxjava2_operators.creating.IntervalObservable;
import ir.taghizadeh.rxjava2_operators.creating.JustObservable;
import ir.taghizadeh.rxjava2_operators.creating.RangeObservable;
import ir.taghizadeh.rxjava2_operators.creating.RepeatObservable;
import ir.taghizadeh.rxjava2_operators.creating.TimerObservable;
import ir.taghizadeh.rxjava2_operators.filtering.DistinctObservable;
import ir.taghizadeh.rxjava2_operators.filtering.FilterObservable;
import ir.taghizadeh.rxjava2_operators.filtering.IgnoreElementsObservable;
import ir.taghizadeh.rxjava2_operators.filtering.SkipLastObservable;
import ir.taghizadeh.rxjava2_operators.filtering.SkipObservable;
import ir.taghizadeh.rxjava2_operators.filtering.TakeLastObservable;
import ir.taghizadeh.rxjava2_operators.filtering.TakeObservable;
import ir.taghizadeh.rxjava2_operators.transforming.BufferObservable;
import ir.taghizadeh.rxjava2_operators.transforming.FlatMapObservable;
import ir.taghizadeh.rxjava2_operators.transforming.GroupByObservable;
import ir.taghizadeh.rxjava2_operators.transforming.MapObservable;
import ir.taghizadeh.rxjava2_operators.transforming.ScanObservable;

public enum EnumOperators {

    COMBINE_LATEST {
        @Override
        public Operators createOperator() {
            return new CombineLatestObservable();
        }
    },
    CONCAT {
        @Override
        public Operators createOperator() {
            return new ConcatObservable();
        }
    },
    JOIN {
        @Override
        public Operators createOperator() {
            return new JoinObservable();
        }
    },
    MERGE {
        @Override
        public Operators createOperator() {
            return new MergeObservable();
        }
    },
    ZIP {
        @Override
        public Operators createOperator() {
            return new ZipObservable();
        }
    },
    ALL {
        @Override
        public Operators createOperator() {
            return new AllObservable();
        }
    },
    AMB {
        @Override
        public Operators createOperator() {
            return new AmbObservable();
        }
    },
    CONTAINS {
        @Override
        public Operators createOperator() {
            return new ContainsObservable();
        }
    },
    DEFAULT_IF_EMPTY {
        @Override
        public Operators createOperator() {
            return new DefaultIfEmptyObservable();
        }
    },
    SEQUENCE_EQUAL {
        @Override
        public Operators createOperator() {
            return new SequenceEqualObservable();
        }
    },
    SKIP_UNTIL {
        @Override
        public Operators createOperator() {
            return new SkipUntilObservable();
        }
    },
    SKIP_WHILE {
        @Override
        public Operators createOperator() {
            return new SkipWhileObservable();
        }
    },
    TAKE_UNTIL {
        @Override
        public Operators createOperator() {
            return new TakeUntilObservable();
        }
    },
    TAKE_WHILE {
        @Override
        public Operators createOperator() {
            return new TakeWhileObservable();
        }
    },
    CREATE {
        @Override
        public Operators createOperator() {
            return new CreateObservable();
        }
    },
    DEFER {
        @Override
        public Operators createOperator() {
            return new DeferObservable();
        }
    },
    FROM {
        @Override
        public Operators createOperator() {
            return new FromObservable();
        }
    },
    INTERVAL {
        @Override
        public Operators createOperator() {
            return new IntervalObservable();
        }
    },
    JUST {
        @Override
        public Operators createOperator() {
            return new JustObservable();
        }
    },
    RANGE {
        @Override
        public Operators createOperator() {
            return new RangeObservable();
        }
    },
    REPEAT {
        @Override
        public Operators createOperator() {
            return new RepeatObservable();
        }
    },
    TIMER {
        @Override
        public Operators createOperator() {
            return new TimerObservable();
        }
    },
    DISTINCT {
        @Override
        public Operators createOperator() {
            return new DistinctObservable();
        }
    },
    FILTER {
        @Override
        public Operators createOperator() {
            return new FilterObservable();
        }
    },
    IGNORE_ELEMENTS {
        @Override
        public Operators createOperator() {
            return new IgnoreElementsObservable();
        }
    },
    SKIP_LAST {
        @Override
        public Operators createOperator() {
            return new SkipLastObservable();
        }
    },
    SKIP {
        @Override
        public Operators createOperator() {
            return new SkipObservable();
        }
    },
    TAKE_LAST {
        @Override
        public Operators createOperator() {
            return new TakeLastObservable();
        }
    },
    TAKE {
        @Override
        public Operators createOperator() {
            return new TakeObservable();
        }
    },
    BUFFER {
        @Override
        public Operators createOperator() {
            return new BufferObservable();
        }
    },
    FLAT_MAP {
        @Override
        public Operators createOperator() {
            return new FlatMapObservable();
        }
    },
    GROUP_BY {
        @Override
        public Operators createOperator() {
            return new GroupByObservable();
        }
    },
    MAP {
        @Override
        public Operators createOperator() {
            return new MapObservable();
        }
    },
    SCAN {
        @Override
        public Operators createOperator() {
            return new ScanObservable();
        }
    };
    public abstract Operators createOperator();
}
