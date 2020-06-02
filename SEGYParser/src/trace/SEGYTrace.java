package trace;

import converter.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class SEGYTrace {
    private int SIZE_HEADER = 240;

    private ReadableByteChannel channel;
    private byte[] bytes;
    private float[] result;
    private int deltaFromPrevious;
    private int previousPoint;

    private int traceSequenceNumberWithinLine;
    private int traceSequenceNumberWithinSEGY;
    private int originalFieldRecordNumber;
    private int traceNumberWithinOriginalFieldRecord;
    private int energySourcePointNumber;
    private int ensembleNumber;
    private int traceNumberWithinEnsemble;
    private short traceIdCode;
    private short numberOfVerticallySummedTraces;
    private short numberOfHorizontallyStackedTraces;
    private short dataUse;
    private int distanceFromCenterSourcePointToCenterReceiver;
    private int elevationReceiverGroup;
    private int surfaceElevationSourceLocation;
    private int sourceDepthBelowSurface;
    private int seismicDatumElevationReceiverGroup;
    private int seismicDatumElevationSource;
    private int waterColumnHeightSourceLocation;
    private int waterColumnHeightReceiverGroup;
    private short scalarAppliedToAllElevations;
    private short scalarAppliedToAllCoord;
    private int sourceX;
    private int sourceY;
    private int groupX;
    private int groupY;
    private short coordinateUnits;
    private short weatheringVelocity;
    private short subweatheringVelocity;
    private short upholeTimeSource;
    private short upholeTimeReceiver;
    private short sourceStaticCorrection;
    private short groupStaticCorrection;
    private short totalStaticApplied;
    private short lagTimeA;
    private short lagTimeB;
    private short delayRecordingTime;
    private short muteTimeStart;
    private short muteTimeEnd;
    private short numberSamplesInThisTrace;
    private short sampleIntervalThisTrace;
    private short gainTypeFieldInstruments;
    private short instrumentGainConstant;
    private short instrumentEarlyOrInitialGain;
    private short correlated;
    private short sweepFrequencyStart;
    private short sweepFrequencyEnd;
    private short sweepLength;
    private short sweepType;
    private short sweepTraceTaperLengthStart;
    private short sweepTraceTaperLengthEnd;
    private short taperType;
    private short aliasFilterFrequency;
    private short aliasFilterSlope;
    private short notchFilterFrequency;
    private short notchFilterSlope;
    private short lowcutFrequency;
    private short highcutFrequency;
    private short lowcutSlope;
    private short highcutSlope;
    private short yearDataRecorded;
    private short dayOfYear;
    private short hourOfDay;
    private short minuteOfHour;
    private short secondOfMinute;
    private short timeBasisCode;
    private short traceWeightingFactor;
    private short geophoneGroupNumberRollSwitchPositionOne;
    private short geophoneGroupNumberTraceNumberWithOriginalFR;
    private short geophoneGroupNumberLastTraceWithOriginalFR;
    private short gapSize;
    private short overTravelAssociated;
    private int XEnsemble;
    private int YEnsemble;
    private int poststackDataInLineNumber;
    private int poststackDataCrossLineNumber;
    private int shotpointNumber;
    private short scalarAppliedToShotpoint;
    private short traceValueMeasurementUnit;
    private short transductionUnit;
    private short deviceID;
    private short scalarAppliedTimes;
    private short sourceTypeOrOrientation;
    private short sourceMeasurementUnit;

    public SEGYTrace(ReadableByteChannel channel) {
        this.channel = channel;
    }

    public SEGYTrace(byte[] bytes) {
        this.bytes = bytes;
    }

    public void InitialHeader() {
        int delta = previousPoint;
        this.traceSequenceNumberWithinLine = Converter.ConvertByteToInt(bytes, delta);
        this.traceSequenceNumberWithinSEGY = Converter.ConvertByteToInt(bytes, 4 + delta);

        this.originalFieldRecordNumber = Converter.ConvertByteToInt(bytes, 8 + delta);

        this.traceNumberWithinOriginalFieldRecord = Converter.ConvertByteToInt(bytes, 12 + delta);

        this.energySourcePointNumber = Converter.ConvertByteToInt(bytes, 16 + delta);

        this.ensembleNumber = Converter.ConvertByteToInt(bytes, 20 + delta);

        this.traceNumberWithinEnsemble = Converter.ConvertByteToInt(bytes, 24 + delta);

        this.traceIdCode = Converter.ConvertByteToShort(bytes, 28 + delta);
        this.numberOfVerticallySummedTraces = Converter.ConvertByteToShort(bytes, 30 + delta);
        this.numberOfHorizontallyStackedTraces = Converter.ConvertByteToShort(bytes, 32 + delta);
        this.dataUse = Converter.ConvertByteToShort(bytes, 34 + delta);
        this.distanceFromCenterSourcePointToCenterReceiver = Converter.ConvertByteToInt(bytes, 36 + delta);
        this.elevationReceiverGroup = Converter.ConvertByteToInt(bytes, 40 + delta);
        this.surfaceElevationSourceLocation = Converter.ConvertByteToInt(bytes, 44 + delta);
        this.sourceDepthBelowSurface = Converter.ConvertByteToInt(bytes, 48 + delta);
        this.seismicDatumElevationReceiverGroup = Converter.ConvertByteToInt(bytes, 52 + delta);
        this.seismicDatumElevationSource = Converter.ConvertByteToInt(bytes, 56 + delta);
        this.waterColumnHeightSourceLocation = Converter.ConvertByteToInt(bytes, 60 + delta);
        this.waterColumnHeightReceiverGroup = Converter.ConvertByteToInt(bytes, 64 + delta);
        this.scalarAppliedToAllElevations = Converter.ConvertByteToShort(bytes, 68 + delta);
        this.scalarAppliedToAllCoord = Converter.ConvertByteToShort(bytes, 70 + delta);
        this.sourceX = Converter.ConvertByteToInt(bytes, 72 + delta);
        this.sourceY = Converter.ConvertByteToInt(bytes, 76 + delta);
        this.groupX = Converter.ConvertByteToInt(bytes, 80 + delta);
        this.groupY = Converter.ConvertByteToInt(bytes, 84 + delta);
        this.coordinateUnits = Converter.ConvertByteToShort(bytes, 88 + delta);
        this.weatheringVelocity = Converter.ConvertByteToShort(bytes, 90 + delta);
        this.subweatheringVelocity = Converter.ConvertByteToShort(bytes, 92 + delta);
        this.upholeTimeSource = Converter.ConvertByteToShort(bytes, 94 + delta);
        this.upholeTimeReceiver = Converter.ConvertByteToShort(bytes, 96 + delta);
        this.sourceStaticCorrection = Converter.ConvertByteToShort(bytes, 98 + delta);
        this.groupStaticCorrection = Converter.ConvertByteToShort(bytes, 100 + delta);
        this.totalStaticApplied = Converter.ConvertByteToShort(bytes, 102 + delta);
        this.lagTimeA = Converter.ConvertByteToShort(bytes, 104 + delta);
        this.lagTimeB = Converter.ConvertByteToShort(bytes, 106 + delta);
        this.delayRecordingTime = Converter.ConvertByteToShort(bytes, 108 + delta);
        this.muteTimeStart = Converter.ConvertByteToShort(bytes, 110 + delta);
        this.muteTimeEnd = Converter.ConvertByteToShort(bytes, 112 + delta);
        this.numberSamplesInThisTrace = Converter.ConvertByteToShort(bytes, 114 + delta);
        this.sampleIntervalThisTrace = Converter.ConvertByteToShort(bytes, 116 + delta);
        this.gainTypeFieldInstruments = Converter.ConvertByteToShort(bytes, 118 + delta);
        this.instrumentGainConstant = Converter.ConvertByteToShort(bytes, 120 + delta);
        this.instrumentEarlyOrInitialGain = Converter.ConvertByteToShort(bytes, 122 + delta);
        this.correlated = Converter.ConvertByteToShort(bytes, 124 + delta);
        this.sweepFrequencyStart = Converter.ConvertByteToShort(bytes, 126 + delta);
        this.sweepFrequencyEnd = Converter.ConvertByteToShort(bytes, 128 + delta);
        this.sweepLength = Converter.ConvertByteToShort(bytes, 130 + delta);
        this.sweepType = Converter.ConvertByteToShort(bytes, 132 + delta);
        this.sweepTraceTaperLengthStart = Converter.ConvertByteToShort(bytes, 134 + delta);
        this.sweepTraceTaperLengthEnd = Converter.ConvertByteToShort(bytes, 136 + delta);
        this.taperType = Converter.ConvertByteToShort(bytes, 138 + delta);
        this.aliasFilterFrequency = Converter.ConvertByteToShort(bytes, 140 + delta);
        this.aliasFilterSlope = Converter.ConvertByteToShort(bytes, 142 + delta);
        this.notchFilterFrequency = Converter.ConvertByteToShort(bytes, 144 + delta);
        this.notchFilterSlope = Converter.ConvertByteToShort(bytes, 146 + delta);
        this.lowcutFrequency = Converter.ConvertByteToShort(bytes, 148 + delta);
        this.highcutFrequency = Converter.ConvertByteToShort(bytes, 150 + delta);
        this.lowcutSlope = Converter.ConvertByteToShort(bytes, 152 + delta);
        this.highcutSlope = Converter.ConvertByteToShort(bytes, 154 + delta);
        this.yearDataRecorded = Converter.ConvertByteToShort(bytes, 156 + delta);
        this.dayOfYear = Converter.ConvertByteToShort(bytes, 158 + delta);
        this.hourOfDay = Converter.ConvertByteToShort(bytes, 160 + delta);
        this.minuteOfHour = Converter.ConvertByteToShort(bytes, 162 + delta);
        this.secondOfMinute = Converter.ConvertByteToShort(bytes, 164 + delta);
        this.timeBasisCode = Converter.ConvertByteToShort(bytes, 166 + delta);
        this.traceWeightingFactor = Converter.ConvertByteToShort(bytes, 168 + delta);
        this.geophoneGroupNumberRollSwitchPositionOne = Converter.ConvertByteToShort(bytes, 170 + delta);
        this.geophoneGroupNumberTraceNumberWithOriginalFR = Converter.ConvertByteToShort(bytes, 172 + delta);
        this.geophoneGroupNumberLastTraceWithOriginalFR = Converter.ConvertByteToShort(bytes, 174 + delta);
        this.gapSize = Converter.ConvertByteToShort(bytes, 176 + delta);
        this.overTravelAssociated = Converter.ConvertByteToShort(bytes, 178 + delta);
        this.XEnsemble = Converter.ConvertByteToInt(bytes, 180 + delta);
        this.YEnsemble = Converter.ConvertByteToInt(bytes, 184 + delta);
        this.poststackDataInLineNumber = Converter.ConvertByteToInt(bytes, 188 + delta);
        this.poststackDataCrossLineNumber = Converter.ConvertByteToInt(bytes, 192 + delta);
        this.shotpointNumber = Converter.ConvertByteToInt(bytes, 196 + delta);
        this.scalarAppliedToShotpoint = Converter.ConvertByteToShort(bytes, 200 + delta);
        this.traceValueMeasurementUnit = Converter.ConvertByteToShort(bytes, 202 + delta);
        this.transductionUnit = Converter.ConvertByteToShort(bytes, 210 + delta);
        this.deviceID = Converter.ConvertByteToShort(bytes, 212 + delta);
        this.scalarAppliedTimes = Converter.ConvertByteToShort(bytes, 214 + delta);
        this.sourceTypeOrOrientation = Converter.ConvertByteToShort(bytes, 216 + delta);
        this.sourceMeasurementUnit = Converter.ConvertByteToShort(bytes, 230 + delta);
    }

    public void readByThread(short dataSampleFormatCode, int readTrace) {
        InitialHeader();
        int delta = previousPoint + 240;
        result = new float[numberSamplesInThisTrace];
        for (int i = 0; i < numberSamplesInThisTrace; i++) {
            result[i] = ByteBuffer.wrap(bytes, delta + 4 * i, 4).getFloat();
        }
        previousPoint = previousPoint + numberSamplesInThisTrace * dataSampleFormatCode;
    }

    public boolean read(short dataSampleFormatCode) throws IOException {
        if (!channel.isOpen())
            return false;
        ByteBuffer traceHeader = ByteBuffer.allocate(240);
        if (channel.read(traceHeader) != 240) {
            channel.close();
            return false;
        }
        bytes = traceHeader.array();
        InitialHeader();
        int formatSize;
        if (dataSampleFormatCode == 1 || dataSampleFormatCode == 2 || dataSampleFormatCode == 4 || dataSampleFormatCode == 5)
            formatSize = 4;
        else if (dataSampleFormatCode == 3)
            formatSize = 2;
        else formatSize = 1;
        int dataLength = formatSize * numberSamplesInThisTrace;
        ByteBuffer trace = ByteBuffer.allocate(dataLength);
        if (channel.read(trace) != dataLength) {
            channel.close();
            return false;
        }
        bytes = trace.array();
        result = new float[bytes.length / 4];
        for (int i = 0; i < numberSamplesInThisTrace; i++)
            result[i] = ByteBuffer.wrap(bytes, 4 * i, 4).getFloat();
        return true;
    }

    public String printTraceHeader() {
        return "\nTrace sequence number within line " + traceSequenceNumberWithinLine +
                "\nTrace sequence number within SEGY " + traceSequenceNumberWithinSEGY +
                "\nOriginal field record number " + originalFieldRecordNumber +
                "\nTrace number within original field record " + traceNumberWithinOriginalFieldRecord +
                "\nEnergy source point number " + energySourcePointNumber +
                "\nEnsemble number " + ensembleNumber +
                "\nTrace number within ensemble " + traceNumberWithinEnsemble +
                "\nTrace ID code " + traceIdCode +
                "\nNumber of vertically summed traces " + numberOfVerticallySummedTraces +
                "\nNumber of horizontally stacked traces " + numberOfHorizontallyStackedTraces +
                "\nData use " + dataUse +
                "\nDistance from center source point to center receiver " + distanceFromCenterSourcePointToCenterReceiver +
                "\nElevation receiver group " + elevationReceiverGroup +
                "\nSurface elevation source location " + surfaceElevationSourceLocation +
                "\nSource depth below surface " + sourceDepthBelowSurface +
                "\nSeismic datum elevation receiver group " + seismicDatumElevationReceiverGroup +
                "\nSesismic datum elevation source " + seismicDatumElevationSource +
                "\nWater column height source location " + waterColumnHeightSourceLocation +
                "\nWater column height receiver group " + waterColumnHeightReceiverGroup +
                "\nScalar applied to all elevation " + scalarAppliedToAllElevations +
                "\nScalar applied to all coordinates " + scalarAppliedToAllCoord +
                "\nSource X " + sourceX +
                "\nSource Y " + sourceY +
                "\nGroup X " + groupX +
                "\nGroup Y " + groupY +
                "\nCoordinate units " + coordinateUnits +
                "\nWeathering velocity " + weatheringVelocity +
                "\nSubweathering velocity " + subweatheringVelocity +
                "\nUphole time source " + upholeTimeSource +
                "\nUphole time receiver " + upholeTimeReceiver +
                "\nSource static correction " + sourceStaticCorrection +
                "\nGroup static correction " + groupStaticCorrection +
                "\nTotal static applied " + totalStaticApplied +
                "\nLag time A " + lagTimeA +
                "\nLag time B " + lagTimeB +
                "\nDelay recording time " + delayRecordingTime +
                "\nMute time start " + muteTimeStart +
                "\nMute time end " + muteTimeEnd +
                "\nNumber samples in this trace " + numberSamplesInThisTrace +
                "\nSample interval this trace " + sampleIntervalThisTrace +
                "\nGain type field instrument " + gainTypeFieldInstruments +
                "\nInstrument gain constant " + instrumentGainConstant +
                "\nInstrument early or initial gain " + instrumentEarlyOrInitialGain +
                "\nCorrelated " + correlated +
                "\nSweep frequency start " + sweepFrequencyStart +
                "\nSweep frequency end " + sweepFrequencyEnd +
                "\nSweep length " + sweepLength +
                "\nSweep type " + sweepType +
                "\nSweep trace taper length start " + sweepTraceTaperLengthStart +
                "\nSweep trace taper length end " + sweepTraceTaperLengthEnd +
                "\nTaper type " + taperType +
                "\nAlias filter frequency " + aliasFilterFrequency +
                "\nAlias filter slope " + aliasFilterSlope +
                "\nNotch filter frequency " + notchFilterFrequency +
                "\nNotch filter slope " + notchFilterSlope +
                "\nLowCut frequency " + lowcutFrequency +
                "\nHightCut frequency " + highcutFrequency +
                "\nLowCut slope " + lowcutSlope +
                "\nHighCut slope " + highcutSlope +
                "\nYear data recorded " + yearDataRecorded +
                "\nDay of year " + dayOfYear +
                "\nHour of day " + hourOfDay +
                "\nMinute of hour " + minuteOfHour +
                "\nSecond of minute " + secondOfMinute +
                "\nTime basis code " + timeBasisCode +
                "\nTrace weighting factor " + traceWeightingFactor +
                "\nGeophone group number roll switch position one " + geophoneGroupNumberRollSwitchPositionOne +
                "\nGeophone group number trace number with original FR " + geophoneGroupNumberTraceNumberWithOriginalFR +
                "\nGeophone group number last trace with original FR " + geophoneGroupNumberLastTraceWithOriginalFR +
                "\nGap size " + gapSize +
                "\nOver travel Associated " + overTravelAssociated +
                "\nX ensemble " + XEnsemble +
                "\nY ensemble " + YEnsemble +
                "\nPoststack data in line number " + poststackDataInLineNumber +
                "\nPoststack data cross-line number " + poststackDataCrossLineNumber +
                "\nShotpoint number " + shotpointNumber +
                "\nScalar applied to shotpoint " + scalarAppliedToShotpoint +
                "\nTrace value measurement unit " + traceValueMeasurementUnit +
                "\nTransduction unit " + transductionUnit +
                "\nDevice ID " + deviceID +
                "\nScalar applied times" + scalarAppliedTimes +
                "\nSource type or orientation " + sourceTypeOrOrientation +
                "\nSource measurement unit " + sourceMeasurementUnit;
    }

    public String printDataTrace() {
        String out = "Index Value";
        for (int i = 0; i < result.length; i++)
            out += "\n" + i + " " + result[i];
        return out;
    }
}
