/*
 * Copyright 2023 ICON Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.protokol7.interfaces.xcall;

import foundation.icon.score.client.ScoreClient;
import score.Address;
import score.annotation.EventLog;

import java.math.BigInteger;

@ScoreClient
public interface CallServiceEvent {
    /*======== At the source CALL_BSH ========*/
    /**
     * Notifies that the requested call message has been sent.
     *
     * @param _from The chain-specific address of the caller
     * @param _to The BTP address of the callee on the destination chain
     * @param _sn The serial number of the request
     * @param _nsn The network serial number of the BTP message
     */
    @EventLog(indexed=3)
    void CallMessageSent(Address _from, String _to, BigInteger _sn, BigInteger _nsn);

    /**
     * Notifies that a response message has arrived for the `_sn` if the request was a two-way message.
     *
     * @param _sn The serial number of the previous request
     * @param _code The response code
     *              (0: Success, -1: Unknown generic failure, >=1: User defined error code)
     * @param _msg The result message if any
     */
    @EventLog(indexed=1)
    void ResponseMessage(BigInteger _sn, int _code, String _msg);

    /**
     * Notifies the user that a rollback operation is required for the request '_sn'.
     *
     * @param _sn The serial number of the previous request
     */
    @EventLog(indexed=1)
    void RollbackMessage(BigInteger _sn);

    /**
     * Notifies that the rollback has been executed.
     *
     * @param _sn The serial number for the rollback
     * @param _code The execution result code
     *              (0: Success, -1: Unknown generic failure, >=1: User defined error code)
     * @param _msg The result message if any
     */
    @EventLog(indexed=1)
    void RollbackExecuted(BigInteger _sn, int _code, String _msg);

    /*======== At the destination CALL_BSH ========*/
    /**
     * Notifies the user that a new call message has arrived.
     *
     * @param _from The BTP address of the caller on the source chain
     * @param _to A string representation of the callee address
     * @param _sn The serial number of the request from the source
     * @param _reqId The request id of the destination chain
     */
    @EventLog(indexed=3)
    void CallMessage(String _from, String _to, BigInteger _sn, BigInteger _reqId);

    /**
     * Notifies that the call message has been executed.
     *
     * @param _reqId The request id for the call message
     * @param _code The execution result code
     *              (0: Success, -1: Unknown generic failure, >=1: User defined error code)
     * @param _msg The result message if any
     */
    @EventLog(indexed=1)
    void CallExecuted(BigInteger _reqId, int _code, String _msg);
}
