package ru.dakon.MoexService.moexclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "corporatebonds", url = "${moex.bonds.corporate.url}")
public interface CorporateBondsClient {
}
