/*
 * Copyright 2023 Protokol7
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

package com.protokol7.types;

import static com.protokol7.utils.AddressUtils.ZERO_ADDRESS;

import score.Address;
import score.Context;
import score.VarDB;
import score.annotation.EventLog;
import score.annotation.External;

public class Ownable {

  // ================================================
  // Consts
  // ================================================
  // Contract class name
  public static final String NAME = "Ownable";

  // ================================================
  // Event Log
  // ================================================
  @EventLog(indexed = 2)
  public void OwnershipPushed(Address previousOwner, Address newOwner) {}

  @EventLog(indexed = 2)
  public void OwnershipPulled(Address previousOwner, Address newOwner) {}

  // ================================================
  // DB Variables
  // ================================================
  private final VarDB<Address> owner = Context.newVarDB(NAME + "_owner", Address.class);
  protected final VarDB<Address> newOwner = Context.newVarDB(NAME + "_newOwner", Address.class);

  public Ownable (Address initialOwner) {
    if (this.owner.get() == null) {
      this.owner.set(initialOwner);
      this.OwnershipPushed(ZERO_ADDRESS, initialOwner);
    }
  }

  protected void onlyPolicy () {
    Address caller = Context.getCaller();

    Context.require(this.owner.get().equals(caller),
      "onlyPolicy: caller is not the owner");
  }

  @External
  public void renounceManagement() {
    onlyPolicy();

    this.OwnershipPushed (this.owner.get(), ZERO_ADDRESS);
    this.owner.set(ZERO_ADDRESS);
  }

  @External
  public void pushManagement (Address newOwner) {
    onlyPolicy();

    Context.require(!newOwner.equals(ZERO_ADDRESS),
      "Ownable: new owner is the zero address");

    this.OwnershipPushed(this.owner.get(), newOwner);
    this.newOwner.set(newOwner);
  }

  @External
  public void pullManagement() {
    Address caller = Context.getCaller();
    Address newOwner = this.newOwner.get();

    Context.require(caller.equals(newOwner), 
      "Ownable: must be new owner to pull");

    Address oldOwner = this.owner.get();
    this.OwnershipPulled(oldOwner, newOwner);
    this.owner.set(newOwner);
  }

  // ================================================
  // Public variable getters
  // ================================================
  @External(readonly = true)
  public Address policy () {
    return this.owner.get();
  }
}
